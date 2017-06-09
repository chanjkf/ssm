package xyz.chanjkf.dao.common;
import org.hibernate.Session;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;

import java.io.Serializable;
import java.util.List;

public interface IGenerics<T extends Serializable>{

    /*获取上下文的当前事务会话*/
    Session getCurrentSession();

    T get(Class<T> clazz, final long id);

    T getActive(Class<T> clazz, final long id);

    /*id为string类型时的get接口*/
    T get(Class<T> clazz, final String id);

    T getActive(Class<T> clazz, final String id);

    /*查询所有满足条件去重的结果*/
    List<T> getDistinct(Class<T> clazz);

    /*查询所有满足条件去重并且有效的结果*/
    List<T> getDistinctActive(Class<T> clazz);

    /* 查询所有满足条件且去重后的有效结果 */
    List<T> getDistinctActive(Class<T> clazz, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件且去重后的结果*/
    List<T> getDistinct(Class<T> clazz, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，未被删除的结果*/
    Page<T> getDistinctActivePage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，未被删除的结果*/
    Page<T> getActivePage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，未被删除的结果总行数*/
    int getDistinctActiveTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder);

    Long create(Class<T> clazz, final T entity);

    T update(Class<T> clazz, final T entity);

    T deactive(Class<T> clazz, final T entity);

    T deactive(Class<T> clazz, final Long id);

    T merge(Class<T> clazz, final T entity);

    void delete(Class<T> clazz, final T entity);

    void deleteById(Class<T> clazz, final long entityId);

    void deleteById(Class<T> clazz, final String entityId);

    /*查询满足条件且去重后的页*/
    Page<T> getDistinctPage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询满足条件且未去重后的页*/
    Page<T> getPage(Class<T> clazz, Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，结果总行数*/
    int getDistinctTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，未被删除的结果总行数*/
    int getActiveTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，结果总行数*/
    int getTotalNumber(Class<T> clazz, DBCriteriaBuilder crBuilder);

    /* 下面两个方法是彻底删除, 即删除数据库中的相应表项 */
    void deleteForce(Class<T> clazz, final T entity);

    void deleteByIdForce(Class<T> clazz, final long entityId);

    /**
     * 批量删除操作
     * @param indexList:索引id list
     * @param entityName：待操作数据库entity
     */
    void deleteBulk(Class<T> clazz, long[] indexList, String entityName);
}
