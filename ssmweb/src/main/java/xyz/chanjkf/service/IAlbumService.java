package xyz.chanjkf.service;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.utils.page.Page;

/**
 * Created by yi on 2017/6/7.
 */
public interface IAlbumService extends IOperations<AlbumEntity> {

    Page<AlbumEntity> getPhotoData(Page<AlbumEntity> page);

    Page<AlbumEntity> getAlbumPages(Integer pageNum, Integer pageSize);

    Long getMaxIdFromDb();
}
