package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.IVideoDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.service.IVideoService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;
import xyz.chanjkf.utils.page.Parameter;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Service("VideoService")
public class VideoService extends AbstractService<VideoEntity> implements IVideoService {

    @Resource(name = "VideoDao")
    private IVideoDao dao;

    @Override
    protected IOperations<VideoEntity> getDao() {
        return dao;
    }


    @Override
    public Page<VideoEntity> getVideoPage(int pageNum, int size) {
        DBCriteriaBuilder builder = new DBCriteriaBuilder();
        CrParams params = new CrParams("VideoEntity");
        builder.getFilterParams().add(params);
        Page<VideoEntity> page = new Page<VideoEntity>(pageNum,size);
        return getDistinctActivePage(page,builder);

    }

    @Override
    public Long getMaxIdFromDb() {
        DBCriteriaBuilder builder = new DBCriteriaBuilder();
        CrParams params = new CrParams("VideoEntity");
        builder.getFilterParams().add(params);
        builder.addOrderBy("id",true);
        List<VideoEntity> entityList = getDistinctActive(builder);
        if(entityList == null || entityList.size() == 0){
            return 1l;
        } else {
            return entityList.get(0).getId();
        }
    }
}
