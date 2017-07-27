package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IRoleDao;
import xyz.chanjkf.dao.IVideoDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.VideoEntity;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("VideoDao")
public class VideoDao extends AbstractHibernateDao<VideoEntity> implements IVideoDao {
    public VideoDao() {
        super(VideoEntity.class);
    }
}
