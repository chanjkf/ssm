package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IPhotoTypeDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.PhotoType;
import xyz.chanjkf.service.IPhotoTypeService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yi on 2017/6/7.
 */
@Service("PhotoTypeService")
public class PhotoTypeService extends AbstractService<PhotoType> implements IPhotoTypeService {

    @Resource(name = "PhotoTypeDao")
    private IPhotoTypeDao dao;

    @Override
    protected IOperations<PhotoType> getDao() {
        return dao;
    }

    @Override
    public Page<PhotoType> getTypePage(Integer pageNum, Integer pageSize) {
        Page<PhotoType> page = new Page<>(pageNum, pageSize);
        return dao.getTypePage(page);
    }

    @Override
    public List<PhotoType> getTypeList() {
        return dao.getTypeList();
    }
}
