package xyz.chanjkf.service;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.RoleEntity;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
public interface IRoleService extends IOperations<RoleEntity> {
    List<RoleEntity> getRoleByUser(Long id);
}
