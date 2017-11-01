package xyz.chanjkf.service;

import xyz.chanjkf.entity.UserEntity;

/**
 * Created by yi on 2017/6/7.
 */
public interface IRegisterService {
    UserEntity registerUser(String name, String password);

    void createRandomData();
}
