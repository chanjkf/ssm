package xyz.chanjkf.service;

import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.utils.BaseException;

import javax.mail.MessagingException;

/**
 * Created by yi on 2017/6/7.
 */
public interface IRegisterService {
    UserEntity registerUser(String name, String password, String email, String path) throws BaseException, MessagingException;

    void createRandomData();
}
