package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IAlbumDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.service.IAlbumService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import java.text.DecimalFormat;

/**
 * Created by yi on 2017/6/7.
 */
@Service("AlbumService")
public class AlbumService extends AbstractService<AlbumEntity> implements IAlbumService {

    @Resource(name = "AlbumDao")
    private IAlbumDao dao;

    @Override
    protected IOperations<AlbumEntity> getDao() {
        return dao;
    }

    @Override
    public Page<AlbumEntity> getPhotoData(Page<AlbumEntity> page) {
        return dao.getPhotoData(page);
    }

    @Override
    public Long getMaxIdFromDb() {
        String sql = "select max(id) from album where active_flag = 1";

        Long a = (Long)getCurrentSession().createSQLQuery(sql).uniqueResult();
        if (a == null) {
            return 0L;
        } else {
            return a;
        }
    }
}
