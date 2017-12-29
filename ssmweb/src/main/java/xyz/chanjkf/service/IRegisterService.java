package xyz.chanjkf.service;

import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.utils.DXPException;

/**
 * Created by yi on 2017/6/7.
 */
public interface IRegisterService {
    UserEntity registerUser(String name, String password, String email) throws DXPException;

    void createRandomData();
}
