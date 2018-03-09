package xyz.chanjkf.service;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.utils.BaseException;
import xyz.chanjkf.utils.page.Page;

/**
 * Created by yi on 2017/6/7.
 */
public interface IUserService extends IOperations<UserEntity> {
    UserEntity findUser(String userName);

    Long findMaxId();

    void activateUser(String validate, Long user_id) throws BaseException;

    void updateUserState(Long userId, boolean onlineFlag);

    void initUserStatus();

    Integer getOnlineNum();

    Page<UserEntity> getUserPages(Integer pageNum, Integer pageSize, boolean onlineFlag);
}
