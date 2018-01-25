package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IUserDetailDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.UserDetailEntity;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("UserDetailDao")
public class UserDetailDao extends AbstractHibernateDao<UserDetailEntity> implements IUserDetailDao {
    public UserDetailDao() {
        super(UserDetailEntity.class);
    }

    @Override
    public UserDetailEntity getEntityByUserId(Long userId) {
        String sql = "select * from user_detail where user_id="+userId;
        return (UserDetailEntity)getCurrentSession().createSQLQuery(sql).addEntity(UserDetailEntity.class).uniqueResult();
    }
}
