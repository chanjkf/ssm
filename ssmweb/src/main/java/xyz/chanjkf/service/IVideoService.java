package xyz.chanjkf.service;

import com.qiniu.common.QiniuException;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.utils.page.Page;

/**
 * Created by yi on 2017/6/7.
 */
public interface IVideoService extends IOperations<VideoEntity> {
    Page<VideoEntity> getVideoPage(int pageNum, int size);

    Long getMaxIdFromDb();

    void increateViewCount(Integer id);

    void deletePhoto(String qiNiuKey) throws QiniuException;
}
