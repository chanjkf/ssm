package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.UserEntity;

import java.math.BigInteger;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("UserDao")
public class UserDao extends AbstractHibernateDao<UserEntity> implements IUserDao{

    public UserDao() {
        super(UserEntity.class);
    }

    @Override
    public void updateUserState(Long userId, boolean onlineFlag) {
        getCurrentSession().createSQLQuery("update user set use_flag="+onlineFlag+" where id ="+userId).executeUpdate();
    }

    @Override
    public void initUserStatus() {
        getCurrentSession().createSQLQuery("update user set use_flag=false").executeUpdate();
    }

    @Override
    public Integer getOnlineNum() {
        BigInteger result = (BigInteger)getCurrentSession().createSQLQuery("select count(1) from user where use_flag = true").uniqueResult();
        return result.intValue();
    }
}
