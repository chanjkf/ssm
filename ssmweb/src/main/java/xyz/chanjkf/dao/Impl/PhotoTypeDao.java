package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IAlbumDao;
import xyz.chanjkf.dao.IPhotoTypeDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.PhotoType;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("PhotoTypeDao")
public class PhotoTypeDao extends AbstractHibernateDao<PhotoType> implements IPhotoTypeDao {
    public PhotoTypeDao() {
        super(PhotoType.class);
    }

    @Override
    public Page<PhotoType> getTypePage(Page<PhotoType> page) {
        DBCriteriaBuilder criteriaBuilder = new DBCriteriaBuilder();
        CrParams params = new CrParams(PhotoType.class.getName());
        criteriaBuilder.getFilterParams().add(params);
        return getDistinctActivePage(page, criteriaBuilder);
    }

    @Override
    public List<PhotoType> getTypeList() {
        return getDistinctActive();
    }
}
