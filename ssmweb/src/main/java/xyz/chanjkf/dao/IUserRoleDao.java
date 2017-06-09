package xyz.chanjkf.dao;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserRoleEntity;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
public interface IUserRoleDao extends IOperations<UserRoleEntity> {
    List<UserRoleEntity> getUserRoleByUserId(Long id);
}
