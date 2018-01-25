package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IUserDetailDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserDetailEntity;
import xyz.chanjkf.service.IUserDetailService;
import xyz.chanjkf.service.common.AbstractService;

import javax.annotation.Resource;

/**
 * Created by yi on 2017/6/7.
 */
@Service("UserDetailService")
public class UserDetailService extends AbstractService<UserDetailEntity> implements IUserDetailService {

    @Resource(name = "UserDetailDao")
    private IUserDetailDao dao;

    @Override
    protected IOperations<UserDetailEntity> getDao() {
        return dao;
    }

    public UserDetailEntity getEntityByUserId(Long userId) {
        return dao.getEntityByUserId(userId);
    }


    @Override
    public void updateDetail(UserDetailEntity entity) {
        this.update(entity);

    }
}
