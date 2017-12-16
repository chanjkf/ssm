package xyz.chanjkf.dao;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.utils.page.Page;

/**
 * Created by yi on 2017/6/7.
 */
public interface IAlbumDao extends IOperations<AlbumEntity> {
    Page<AlbumEntity> getPhotoData(Page<AlbumEntity> page);
}
