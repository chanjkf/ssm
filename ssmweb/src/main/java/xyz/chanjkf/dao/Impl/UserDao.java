package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.UserEntity;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("UserDao")
public class UserDao extends AbstractHibernateDao<UserEntity> implements IUserDao{
    public UserDao() {
        super(UserEntity.class);
    }
}
