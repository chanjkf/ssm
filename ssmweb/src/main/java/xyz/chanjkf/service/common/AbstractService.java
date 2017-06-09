package xyz.chanjkf.service.common;


import org.hibernate.Session;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

    protected abstract IOperations<T> getDao();

    @Override
    /*获取上下文的当前事务会话*/
    public Session getCurrentSession() {
        return getDao().getCurrentSession();
    }

    @Override
    public T get(final long id) {
        return getDao().get(id);
    }

    @Override
    public T getActive(final long id) {
        return getDao().getActive(id);
    }

    @Override
    public T get(final String id) {
        return getDao().get(id);
    }

    @Override
    public T getActive(final String id) {
        return getDao().getActive(id);
    }

    @Override
    /*查询所有满足条件去重的结果*/
    public List<T> getDistinct() {
        return getDao().getDistinct();
    }

    @Override
    /*查询所有满足条件去重并且有效的结果*/
    public List<T> getDistinctActive() {
        return getDao().getDistinctActive();
    }

    @Override
    /*查询所有满足条件且去重后的结果*/
    public List<T> getDistinctActive(DBCriteriaBuilder crBuilder) {
        Object o =getDao();
        return getDao().getDistinctActive(crBuilder);
    }


    @Override
    /*查询所有满足条件且去重后的结果*/
    public List<T> getDistinct(DBCriteriaBuilder crBuilder) {
        return getDao().getDistinct(crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果*/
    public Page<T> getDistinctActivePage(Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctActivePage(page, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果总行数*/
    public int getDistinctActiveTotalNumber(DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctActiveTotalNumber(crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果*/
    public Page<T> getActivePage(Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getActivePage(page, crBuilder);
    }

    @Override
    public Long create(final T entity) {
        return getDao().create(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().update(entity);
    }

    @Override
    public T deactive(final T entity) {
        return getDao().deactive(entity);
    }

    @Override
    public T deactive(final Long id) {
        return getDao().deactive(id);
    }

    @Override
    public T merge(final T entity) {
        return getDao().merge(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(long entityId) {
        getDao().deleteById(entityId);
    }

    @Override
    public void deleteById(String entityId) {
        getDao().deleteById(entityId);
    }

    @Override
    public void deleteByIdForce(long entityId) {
        getDao().deleteByIdForce(entityId);
    }

    @Override
    /*查询满足条件且去重后的页*/
    public Page<T> getDistinctPage(Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctPage(page, crBuilder);
    }

    @Override
    /*查询满足条件且未去重后的页*/
    public Page<T> getPage(Page<T> page, DBCriteriaBuilder crBuilder) {
        return getDao().getPage(page, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，结果总行数*/
    public int getDistinctTotalNumber(DBCriteriaBuilder crBuilder) {
        return getDao().getDistinctTotalNumber(crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果总行数*/
    public int getActiveTotalNumber(DBCriteriaBuilder crBuilder) {
        return getDao().getTotalNumber(crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，结果总行数*/
    public int getTotalNumber(DBCriteriaBuilder crBuilder) {
        return getDao().getTotalNumber(crBuilder);
    }

    @Override
    public void deleteForce(T entity) {
        getDao().deleteForce(entity);
    }

    @Override
    public void deleteBulk(long[] indexList, String entityName){
        getDao().deleteBulk(indexList, entityName);
    }

    /**
     * 根据所有旧创建人，更新所有创建人ID
     * @param newCreatorId
     * @param entityName
     * @param conditions 满足条件的字段
     */
    @Override
    public void updateAllCreatorId(Long newCreatorId, String entityName, Map<String, String> conditions) {
        getDao().updateAllCreatorId(newCreatorId, entityName, conditions);
    }

}
