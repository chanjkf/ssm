package xyz.chanjkf.service;

import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.utils.DXPException;
import xyz.chanjkf.utils.page.Page;

import java.util.List;

/**
 * 主题分类service
 */
public interface IElasticSearchService {

    boolean health();

    String getResetStatus();

    boolean saveUser(UserEntity entity);

    void initUserStructure()throws DXPException;

    Page<UserEntity> searchUser(Page<UserEntity> page, String keyWord, String searchType) throws DXPException;
}
