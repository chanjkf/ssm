package xyz.chanjkf.dao.Impl;

import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IAlbumDao;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;
import xyz.chanjkf.utils.page.Parameter;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("AlbumDao")
public class AlbumDao extends AbstractHibernateDao<AlbumEntity> implements IAlbumDao {
    public AlbumDao() {
        super(AlbumEntity.class);
    }

    @Override
    public Page<AlbumEntity> getPhotoData(Page<AlbumEntity> page) {
        DBCriteriaBuilder criteriaBuilder = new DBCriteriaBuilder();
        CrParams params = new CrParams(AlbumEntity.class.getName());
        criteriaBuilder.getFilterParams().add(params);
        return getDistinctActivePage(page, criteriaBuilder);
    }

    @Override
    public Page<AlbumEntity> getAlbumPages(Page<AlbumEntity> page) {
        Criteria c = getCurrentSession().createCriteria(AlbumEntity.class);
        c.add(Restrictions.eq("",""));
        final List list = c.list();


        return null;
    }
}
