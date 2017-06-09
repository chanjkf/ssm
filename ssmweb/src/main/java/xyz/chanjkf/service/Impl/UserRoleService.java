package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IUserRoleDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserRoleEntity;
import xyz.chanjkf.service.IUserRoleService;
import xyz.chanjkf.service.common.AbstractService;

import javax.annotation.Resource;

/**
 * Created by yi on 2017/6/7.
 */
@Service("UserRoleService")
public class UserRoleService extends AbstractService<UserRoleEntity> implements IUserRoleService {

    @Resource(name = "UserRoleDao")
    private IUserRoleDao dao;

    @Override
    protected IOperations<UserRoleEntity> getDao() {
        return dao;
    }
}
