package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.UserRoleEntity;
import xyz.chanjkf.service.IRegisterService;
import xyz.chanjkf.service.IRoleService;
import xyz.chanjkf.service.IUserRoleService;
import xyz.chanjkf.service.IUserService;

import javax.annotation.Resource;

/**
 * Created by yi on 2017/6/7.
 */
@Service("RegisterService")
public class RegisterService implements IRegisterService {

    @Resource(name = "RoleService")
    private IRoleService roleService;

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "UserRoleService")
    private IUserRoleService userRoleService;

    @Override
    public UserEntity registerUser(String name, String password) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("ROLE_ADMIN");
        roleEntity.setDescription("平台管理员");
        roleService.create(roleEntity);

        UserEntity user = new UserEntity();
        user.setUserName(name);
        user.setUserPassword(password);
        userService.create(user);
        Long userId = user.getId();

        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserName(name);
        userRoleEntity.setRoleEntity(roleEntity);
        userRoleEntity.setUserId(userId);
        userRoleService.create(userRoleEntity);
        return user;
    }
}
