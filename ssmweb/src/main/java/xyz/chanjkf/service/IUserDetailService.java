package xyz.chanjkf.service;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.UserDetailEntity;
import xyz.chanjkf.utils.page.Page;

/**
 * Created by yi on 2017/6/7.
 */
public interface IUserDetailService extends IOperations<UserDetailEntity> {

    void updateDetail(UserDetailEntity entity);
}
