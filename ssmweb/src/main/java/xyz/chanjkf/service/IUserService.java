package xyz.chanjkf.service;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;

/**
 * Created by yi on 2017/6/7.
 */
public interface IUserService extends IOperations<UserEntity> {
    UserEntity findUser(String userName);
}
