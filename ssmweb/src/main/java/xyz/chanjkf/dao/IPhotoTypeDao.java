package xyz.chanjkf.dao;

import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.PhotoType;
import xyz.chanjkf.utils.page.Page;

import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
public interface IPhotoTypeDao extends IOperations<PhotoType> {

    Page<PhotoType> getTypePage(Page<PhotoType> page);

    List<PhotoType> getTypeList();
}
