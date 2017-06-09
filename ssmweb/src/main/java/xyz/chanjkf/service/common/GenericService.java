package xyz.chanjkf.service.common;

import org.hibernate.Session;
import xyz.chanjkf.dao.common.IGenerics;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;

import java.io.Serializable;
import java.util.List;

public abstract class GenericService<T extends Serializable> implements IGenerics<T> {

    protected abstract IGenerics<T> getDao();

    @Override
    /*获取上下文的当前事务会话*/
    public Session getCurrentSession() {
        return getDao().getCurrentSession();
    }

    @Override
    public T get(Class<T> clazz, final long id) {
        return getDao().get(clazz, id);
    }

    @Override
    public T getActive(Class<T> clazz, final long id) {
        return getDao().getActive(clazz, id);
    }

    @Override
    public T get(Class<T> clazz, final String id) {
        return getDao().get(clazz, id);
    }

    @Override
    public T getActive(Class<T> clazz, final String id) {
        return getDao().getActive(clazz, id);
    }

    @Override
    /*查询所有满足条件去重的结果*/
    public List<T> getDistinct(Class<T> clazz) {
        return getDao().getDistinct(clazz);
    }

    @Override
    /*查询所有满足条件去重并且有效的结果*/
    public List<T> getDistinctActive(Class<T> clazz) {
        return getDao().getDistinctActive(clazz);
    }

    @Override
    /*查询所有满足条件且去重后的结果*/
    public List<T> getDistinctActive(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctActive(clazz, crBuilder);
    }


    @Override
    /*查询所有满足条件且去重后的结果*/
    public List<T> getDistinct(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinct(clazz, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果*/
    public Page<T> getDistinctActivePage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctActivePage(clazz, page, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果总行数*/
    public int getDistinctActiveTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctActiveTotalNumber(clazz, crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果*/
    public Page<T> getActivePage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getActivePage(clazz, page, crBuilder);
    }

    @Override
    public Long create(Class<T> clazz, final T entity) {
        return getDao().create(clazz, entity);
    }

    @Override
    public T update(Class<T> clazz, final T entity) {
        return getDao().update(clazz, entity);
    }

    @Override
    public T deactive(Class<T> clazz, final T entity) {
        return getDao().deactive(clazz, entity);
    }

    @Override
    public T deactive(Class<T> clazz, final Long id) {
        return getDao().deactive(clazz, id);
    }

    @Override
    public T merge(Class<T> clazz, final T entity) {
        return getDao().merge(clazz, entity);
    }

    @Override
    public void delete(Class<T> clazz, final T entity) {
        getDao().delete(clazz, entity);
    }

    @Override
    public void deleteById(Class<T> clazz, long entityId) {
        getDao().deleteById(clazz, entityId);
    }

    @Override
    public void deleteById(Class<T> clazz, String entityId) {
        getDao().deleteById(clazz, entityId);
    }

    @Override
    public void deleteByIdForce(Class<T> clazz, long entityId) {
        getDao().deleteByIdForce(clazz, entityId);
    }

    @Override
    /*查询满足条件且去重后的页*/
    public Page<T> getDistinctPage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctPage(clazz, page, crBuilder);
    }

    @Override
    /*查询满足条件且未去重后的页*/
    public Page<T> getPage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getPage(clazz, page, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，结果总行数*/
    public int getDistinctTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctTotalNumber(clazz, crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果总行数*/
    public int getActiveTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getTotalNumber(clazz, crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，结果总行数*/
    public int getTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder) {
        return getDao().getTotalNumber(clazz, crBuilder);
    }

    @Override
    public void deleteForce(Class<T> clazz, T entity) {
        getDao().deleteForce(clazz, entity);
    }

    @Override
    public void deleteBulk(Class<T> clazz, long[] indexList, String entityName){
        getDao().deleteBulk(clazz, indexList, entityName);
    }

}
