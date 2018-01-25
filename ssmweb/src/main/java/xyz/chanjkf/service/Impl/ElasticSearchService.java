package xyz.chanjkf.service.Impl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.model.ElasticSearchResetModel;
import xyz.chanjkf.service.IElasticSearchService;
import xyz.chanjkf.utils.*;
import org.elasticsearch.client.Client;
import xyz.chanjkf.utils.page.Page;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author yi
 * @date 2017/9/12
 */
@Service("ElasticSearchService")
public class ElasticSearchService implements IElasticSearchService{

    Log log = new Log();

    private Client client = null;

    private ElasticSearchResetModel elasticSearchResetModel = new ElasticSearchResetModel();

    @Override
    public boolean health() {
        ClusterHealthResponse healthResponse = client.admin().cluster().health(new ClusterHealthRequest()).actionGet();
        if(!"RED".equalsIgnoreCase(healthResponse.getStatus().name())) {
            return true;
        }

        return false;
    }

    @Override
    public String getResetStatus() {
        if ("RUNNING".equals(elasticSearchResetModel.getStatus())) {
            return "搜索引擎正在进行数据重置中，当前状态：" + elasticSearchResetModel.getStatus() + "；开始时间：" + BaseTime
                    .formatDateTime(elasticSearchResetModel
                            .getBeginTime());
        }

        if ("SUCCESS".equals(elasticSearchResetModel.getStatus())) {
            return "搜索引擎数据重置成功，当前状态：" + elasticSearchResetModel.getStatus() + "；开始时间："
                    + BaseTime.formatDateTime(elasticSearchResetModel.getBeginTime())
                    + "；结束时间：" + BaseTime.formatDateTime(elasticSearchResetModel.getFinishedTime());
        }

        if ("FAILED".equals(elasticSearchResetModel.getStatus())) {
            return "搜索引擎数据重置失败，当前状态：" + elasticSearchResetModel.getStatus() + "；开始时间："
                    + BaseTime.formatDateTime(elasticSearchResetModel.getBeginTime())
                    + "；结束时间：" + BaseTime.formatDateTime(elasticSearchResetModel.getFinishedTime())
                    + "；失败原因：" + elasticSearchResetModel.getErrorReason();
        }

        return "搜索引擎尚未进行数据重置！";
    }




    @Override
    public boolean saveVideo(VideoEntity entity) {
        if (entity == null) {
            return true;
        }
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("id",entity.getId());
        userMap.put("name",entity.getName());
        userMap.put("description",entity.getDescription());
        try {
            client.prepareIndex(Const.INDEX_ALIAS, Const.USER_TABLE)
                    .setSource(userMap).setId(entity.getId().toString())
                    .execute()
                    .actionGet();
            return true;
        } catch (ElasticsearchException e) {
            log.error("[ES]: add user[userId=" + entity.getId() + "] failed", e);
            return false;
        }
    }

    @Override
    public void initVideoStructure() throws BaseException {
        deleteIndex(Const.INDEX_ALIAS);
        createUserStructure(Const.INDEX_ALIAS, Const.USER_TABLE);
        log.info("[ES]: initialize index[" + Const.INDEX_ALIAS + "] and type[" + Const.USER_TABLE + "] success！");

    }
    /**
     * 按照索引名删除索引
     *
     * @param indexName
     */
    private void deleteIndex(String indexName) throws BaseException {

        try {
            client.admin().indices().prepareDelete(indexName).execute().actionGet();
        } catch (ElasticsearchException e) {
            log.error("[ES]: delete index [" + indexName + "] failed", e);
        }
    }

    @Override
    public Page<VideoEntity> searchVideo(Page<VideoEntity> page, String keyWord, String searchType) throws BaseException {
        SearchRequestBuilder builder = this.client.prepareSearch(Const.INDEX_ALIAS).setTypes(Const.USER_TABLE);
        BoolQueryBuilder query = QueryBuilders.boolQuery();
        if(StringUtils.isNotEmpty(keyWord)) {
            BoolQueryBuilder keyWordBuilder = QueryBuilders.boolQuery()
                    .should(QueryBuilders.matchQuery("search_name", keyWord).operator(MatchQueryBuilder.Operator.AND))
                    .should(QueryBuilders.wildcardQuery("name", "*" + keyWord + "*"));
            query.must(keyWordBuilder);
        }

        SearchHit[] searchHits;
        try {
            SearchResponse response = builder.setQuery(query).addFields(
                    "id", "name", "description").setFrom(page.getStartRow())
                    .setSize(page.getPageSize()).execute().actionGet();
            page.setTotalRows(response.getHits().totalHits());
            searchHits = response.getHits().getHits();
        } catch (ElasticsearchException e) {
            throw new BaseException("[ES]: search data failed!", e);
        }
        VideoEntity model;
        if (searchHits.length > 0) {
            for (SearchHit searchHit : searchHits) {
                try {
                    Map<String, SearchHitField> fields = searchHit.getFields();
                    model = parseSearchFields(fields);
                    page.getResult().add(model);
                } catch (Exception e) {
                    log.error("[ES]: search result translate failed!", e);
                }
            }
        }

        return page;
    }
    private VideoEntity parseSearchFields(Map<String, SearchHitField> fields) throws InvocationTargetException, IllegalAccessException {
        VideoEntity model = new VideoEntity();

        SearchHitField hitField;
        Map<String, Object> resultMap = new HashMap<String, Object>(500);
        for(Map.Entry<String, SearchHitField> entry : fields.entrySet()) {
            hitField = entry.getValue();
            resultMap.put(entry.getKey(), hitField.getValue());
        }

        BeanUtils.populate(model, resultMap);

        return model;
    }

    /**
     * 根据索引名进行结构初始化
     *
     * @param indexName
     */
    private void createUserStructure(String indexName, String typeName) throws BaseException {
        try {
            client.admin().indices().prepareCreate(indexName).execute().actionGet();

            PutMappingRequest putMappingRequest = Requests.putMappingRequest(indexName).type(typeName)
                    .source(createXContentBuilder(typeName));
            client.admin().indices().putMapping(putMappingRequest).actionGet();
        } catch (Exception e) {
            throw new BaseException("[ES]: initialize index[" + indexName + "] and type[" + typeName + "] failed！", e);
        }
    }

    private XContentBuilder createXContentBuilder(String typeName) throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject(typeName)
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .endObject()
                .startObject("name")
                .field("analyzer", "ik")
                .field("index", "analyzed")
                .field("type", "String")
                .endObject()
                .startObject("description")
                .field("type", "string")
                .field("analyzer", "ik")
                .field("index", "analyzed")
                .endObject();
        return builder;
    }
    @PostConstruct
    public void initClient() {
        Integer useFlag = CommonUtil.USE_FLAG;
        boolean flag = false;
        if (useFlag != null && useFlag == 1) {
            flag = true;
        }
        if (flag) {
            try {
                String path = this.getClass().getResource("/").getPath() + "es.properties";
                path = URLDecoder.decode(path, "utf-8");
                Resource resource = new FileSystemResource(path);
                Properties configs = PropertiesLoaderUtils.loadProperties(resource);


                String target = configs.getProperty("es_transport_client");
                if (StringUtils.isEmpty(target)) {
                    log.warn("[ES]: search cluster config failed.");
                }
                String[] targetlist = target.split(";");
                int port = (Integer.parseInt(configs.getProperty("es_transport_port")));
                TransportAddress[] mylist = new TransportAddress[targetlist.length];
                int i = 0;
                for (String loop : targetlist) {
                    mylist[i] = new InetSocketTransportAddress(loop, port);
                    i++;
                }
                this.client = new TransportClient().addTransportAddresses(mylist);
            } catch (ElasticsearchException e) {
                log.error("[ES]: get search cluster client failed.", e);
            } catch (Exception e) {
                log.error(e);
            }
        }

    }

    @PreDestroy
    public void close() {
        client.close();
    }
}
