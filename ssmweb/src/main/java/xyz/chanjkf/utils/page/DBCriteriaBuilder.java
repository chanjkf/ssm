package xyz.chanjkf.utils.page;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import xyz.chanjkf.utils.DXPUtil;

import java.util.*;

public class DBCriteriaBuilder {
    /*是否去重，多级关联查询时，可能搜索出相同的entity*/
    private boolean Distinct;
    private Map<String, Boolean> ordersBy;
    private Criteria cr;
    private List<CrParams> filterParams = new ArrayList<CrParams>();

    public DBCriteriaBuilder() {
        this.ordersBy = new HashMap<String, Boolean>();
        this.Distinct = true;
    }

    public boolean isDistinct() {
        return Distinct;
    }

    public void setDistinct(boolean Distinct) {
        this.Distinct = Distinct;
    }

    public Criteria getCr() {
        return cr;
    }

    public void setCr(Criteria cr) {
        this.cr = cr;
    }

    public Map<String, Boolean> getOrdersBy() {
        return ordersBy;
    }

    public void setOrdersBy(Map<String, Boolean> ordersBy) {
        this.ordersBy = ordersBy;
    }

    public List<CrParams> getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(List<CrParams> filterParams) {
        this.filterParams = filterParams;
    }

    /*添加一个添加到排序链表*/
    public void addOrderBy(String orderBy, Boolean orderAscending) {
        this.ordersBy.put(orderBy, orderAscending);
    }

    /*设置查询限制条件*/
    private void setRestrictionCondition(){
        Criteria SubCr;

        for (CrParams crParams : filterParams) {
            /*创建下一级查询表*/
            if (crParams.getEntityName().equals(filterParams.get(0).getEntityName())){
                SubCr = cr;
            }
            else {
                SubCr = cr.createCriteria(crParams.getEntityName());         //创建二级cr用实体在一级实体中的成员名
            }
            for (Parameter parameter : crParams.getParamList()){
                if (parameter.getCondition() == ConditionType.eq) {
                    SubCr.add(Restrictions.eq(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.between){
                    SubCr.add(Restrictions.between(parameter.getName(), parameter.getValue(), parameter.getValue2()));
                }else if (parameter.getCondition() == ConditionType.like){
                    SubCr.add(Restrictions.like(parameter.getName(), DXPUtil.escapeSQL(parameter.getValue().toString()), MatchMode.ANYWHERE));
                }else if (parameter.getCondition() == ConditionType.ilike){
                    SubCr.add(Restrictions.ilike(parameter.getName(), DXPUtil.escapeSQL(parameter.getValue().toString()), MatchMode.ANYWHERE));
                }else if (parameter.getCondition() == ConditionType.ne){
                    SubCr.add(Restrictions.ne(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.gt){
                    SubCr.add(Restrictions.gt(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.lt){
                    SubCr.add(Restrictions.lt(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.ge){
                    SubCr.add(Restrictions.ge(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.le) {
                    SubCr.add(Restrictions.le(parameter.getName(), parameter.getValue()));
                }else if (parameter.getCondition() == ConditionType.isNotNull){
                    SubCr.add(Restrictions.isNotNull(parameter.getName()));
                } else if (parameter.getCondition() == ConditionType.isNull) {
                    SubCr.add(Restrictions.isNull(parameter.getName()));
                } else if (parameter.getCondition() == ConditionType.in){
                    SubCr.add(Restrictions.in(parameter.getName(), (Object[]) parameter.getValue()));
                }
            }
        }

        /*设置排序规则排序*/
        Iterator it = ordersBy.entrySet().iterator();
        String orderBy;
        String booleanValue;
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            booleanValue = entry.getValue().toString();
            orderBy = entry.getKey().toString();
            if (true == Boolean.valueOf(booleanValue)) {
                cr.addOrder(Order.asc(orderBy));
            } else {
                cr.addOrder(Order.desc(orderBy));
            }
        }

        return ;
    }

    /*不分页查询，获取全部链表*/
    public Criteria buildAll(Session curSession, Class clazz){
        Criteria cr = curSession.createCriteria(clazz);
        this.cr = cr;

        /*设置查询条件*/
        setRestrictionCondition();

        if (true == this.Distinct) {
            /*该接口是在分页完成后再去重*/
            cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }

        return cr;
    }

    /*获取表主键（表只有一个主键的情况）对应的内存实体变量名*/
    private String getPrimaryKeyVariable (Session curSession, Class clazz){
        ClassMetadata metadata = curSession.getSessionFactory().getClassMetadata(clazz);
        String IdsStr = ((SingleTableEntityPersister) metadata).getIdentifierPropertyName();
        return IdsStr;
    }

    /*去重后的总行数，只支持表是唯一主键的情况*/
    public int getDistinctTotalRows(Session curSession, Class clazz){
        int toltalRows = 0;

        /*1. 设置查询条件*/
        cr = curSession.createCriteria(clazz);
        setRestrictionCondition();

        /*2. 获取满足查询条件的去重id链表*/
        ProjectionList projectionList = Projections.projectionList();
        String IdsStr = getPrimaryKeyVariable(curSession, clazz);
        if (null != IdsStr) {
            projectionList.add(Projections.property(IdsStr));
            cr.setProjection(Projections.distinct(projectionList));
            toltalRows =  ((Long)cr.setProjection(Projections.countDistinct(IdsStr)).uniqueResult()).intValue();
        }

        return toltalRows;
    }

    /*未去重的总行数*/
    public int getTotalRows(Session curSession, Class clazz){
        Criteria cr = curSession.createCriteria(clazz);
        this.cr = cr;

        /*设置查询条件*/
        setRestrictionCondition();

        cr.setProjection(Projections.rowCount());
        return Integer.parseInt(cr.list().get(0).toString());
    }

    /*先去重后分页，组装指定也的查询参数
    * 查询不到结果返回null*/
    private Criteria buildDistinctOnePageCr(Session curSession, Class clazz, int startRows, int pageSize){
        /*1. 设置查询条件*/
        cr = curSession.createCriteria(clazz);
        setRestrictionCondition();

        /*2. 获取满足查询条件的去重id链表*/
        ProjectionList projectionList = Projections.projectionList();
        String IdsStr = getPrimaryKeyVariable(curSession, clazz);
        if (null != IdsStr){
            projectionList.add(Projections.property(IdsStr));
        }
        cr.setProjection(Projections.distinct(projectionList));

        /*3. 对应去重的id链表分页*/
        if (0 != startRows) {
            cr.setFirstResult(startRows);
        }

        if (0 != pageSize){
            cr.setMaxResults(pageSize);
        }
        List<Object[]> ids = cr.list();

        if ((null == ids) || (ids.size() <= 0)) {
            cr = null;
        }else {
            /*4. 根据id链表获取当前页的表集合*/
            cr = curSession.createCriteria(clazz);
            cr.add(Restrictions.in("id", ids));

            /*设置排序规则排序*/
            Iterator it = ordersBy.entrySet().iterator();
            String orderBy;
            String booleanValue;
            while(it.hasNext()){
                Map.Entry entry = (Map.Entry)it.next();
                booleanValue = entry.getValue().toString();
                orderBy = entry.getKey().toString();
                if (true == Boolean.valueOf(booleanValue)) {
                    cr.addOrder(Order.asc(orderBy));
                } else {
                    cr.addOrder(Order.desc(orderBy));
                }
            }
        }

        return cr;
    }

    /*不重后分页，组装指定也的查询参数*/
    private Criteria buildOnePageCr(Session curSession, Class clazz, int startRows, int pageSize){
        /*设置查询条件*/
        cr = curSession.createCriteria(clazz);
        setRestrictionCondition();

        /*分页*/
        if (0 != startRows) {
            cr.setFirstResult(startRows);
        }

        if (0 != pageSize){
            cr.setMaxResults(pageSize);
        }

        return cr;
    }

    /*分页查询，获取某一页数据，只支持一对多，多对多映射成LAZY情况*/
    public Criteria buildOnePage(Session curSession, Class clazz, int startRows, int pageSize){

        if (true == this.Distinct){
            /*先去重后分页*/
            buildDistinctOnePageCr(curSession, clazz, startRows, pageSize);
        }else {
            /*不去重后分页*/
            buildOnePageCr(curSession, clazz, startRows, pageSize);
        }

        return cr;
    }
}
