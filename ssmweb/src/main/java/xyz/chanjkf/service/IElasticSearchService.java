package xyz.chanjkf.service;

import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.utils.DXPException;
import xyz.chanjkf.utils.page.Page;

import java.util.List;

/**
 * 主题分类service
 */
public interface IElasticSearchService {

    boolean health();

    String getResetStatus();

    /**
     * 保存视频信息到es
     * @param entity
     * @return
     */
    boolean saveVideo(VideoEntity entity);

    /**
     * 删除之前的缩影初始化
     * @throws DXPException
     */
    void initVideoStructure()throws DXPException;

    /**
     * 查询
     * @param page
     * @param keyWord
     * @param searchType
     * @return
     * @throws DXPException
     */
    Page<VideoEntity> searchVideo(Page<VideoEntity> page, String keyWord, String searchType) throws DXPException;
}
