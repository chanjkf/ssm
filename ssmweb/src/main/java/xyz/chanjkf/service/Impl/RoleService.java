package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IRoleDao;
import xyz.chanjkf.dao.IUserRoleDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.UserRoleEntity;
import xyz.chanjkf.service.IRoleService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Parameter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Service("RoleService")
public class RoleService extends AbstractService<RoleEntity> implements IRoleService {
    @Resource(name = "RoleDao")
    private IRoleDao dao;

    @Resource(name = "UserRoleDao")
    private IUserRoleDao userRoleDao;

    @Override
    protected IOperations<RoleEntity> getDao() {
        return dao;
    }

    @Override
    public List<RoleEntity> getRoleByUser(Long id) {
        List<UserRoleEntity> userRoleList =  userRoleDao.getUserRoleByUserId(id);
        List<RoleEntity>roleList = new ArrayList<RoleEntity>();
        for (UserRoleEntity userRole:userRoleList) {
            RoleEntity roleEntity = userRole.getRoleEntity();
            roleList.add(roleEntity);
        }
        return roleList;
    }
}
