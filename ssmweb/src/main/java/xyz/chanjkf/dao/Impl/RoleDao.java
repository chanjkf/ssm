package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import xyz.chanjkf.dao.IRoleDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.UserEntity;

import javax.management.relation.Role;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("RoleDao")
public class RoleDao extends AbstractHibernateDao<RoleEntity> implements IRoleDao{
    public RoleDao() {
        super(RoleEntity.class);
    }
}
