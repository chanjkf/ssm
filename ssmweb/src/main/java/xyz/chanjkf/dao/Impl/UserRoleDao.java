package xyz.chanjkf.dao.Impl;

import org.springframework.stereotype.Repository;
import sun.security.pkcs11.wrapper.CK_RSA_PKCS_OAEP_PARAMS;
import xyz.chanjkf.dao.IUserRoleDao;
import xyz.chanjkf.dao.common.AbstractHibernateDao;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.UserRoleEntity;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Parameter;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Repository("UserRoleDao")
public class UserRoleDao extends AbstractHibernateDao<UserRoleEntity> implements IUserRoleDao {
    public UserRoleDao() {
        super(UserRoleEntity.class);
    }
    @Override
    public List<UserRoleEntity> getUserRoleByUserId(Long id) {
        DBCriteriaBuilder builder = new DBCriteriaBuilder();
        CrParams params = new CrParams("UserRoleEntity");
        Parameter parameter = new Parameter("userId",id);
        params.getParamList().add(parameter);
        builder.getFilterParams().add(params);
        return getDistinctActive(builder);
    }
}
