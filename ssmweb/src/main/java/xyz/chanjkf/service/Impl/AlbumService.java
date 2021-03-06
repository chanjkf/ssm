package xyz.chanjkf.service.Impl;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IAlbumDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.service.IAlbumService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.QiNiuOSSUtil;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import java.math.BigInteger;

/**
 * Created by yi on 2017/6/7.
 */
@Service("AlbumService")
public class AlbumService extends AbstractService<AlbumEntity> implements IAlbumService {

    @Resource(name = "AlbumDao")
    private IAlbumDao dao;

    @Override
    protected IOperations<AlbumEntity> getDao() {
        return dao;
    }

    @Override
    public Page<AlbumEntity> getPhotoData(Page<AlbumEntity> page) {
        return dao.getPhotoData(page);
    }

    @Override
    public Long getMaxIdFromDb() {
        String sql = "select max(id) from album where active_flag = 1";
        Object result = getCurrentSession().createSQLQuery(sql).uniqueResult();
        if (result == null) {
            return 0L;
        } else {
            return ((BigInteger)result).longValue();
        }
    }

    @Override
    public void deletePhoto(String qiNiuKey) throws QiniuException {
        Auth auth = Auth.create(QiNiuOSSUtil.accessKey, QiNiuOSSUtil.secretKey);
        BucketManager bucketManager = new BucketManager(auth, QiNiuOSSUtil.getConfiguration());
        bucketManager.delete(QiNiuOSSUtil.bucket, qiNiuKey);
    }

    @Override
    public Page<AlbumEntity> getAlbumPages(Integer pageNum, Integer pageSize) {
        Page<AlbumEntity> page = new Page<>(pageNum, pageSize);
        return dao.getAlbumPages(page);
    }
}
