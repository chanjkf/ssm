package xyz.chanjkf.dao.common;

import org.hibernate.Session;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/*
 * 通用的操作接口
 */
public interface IOperations<T extends Serializable> {
    /*获取上下文的当前事务会话*/
    Session getCurrentSession();

    T get(final long id);

    T getActive(final long id);

    /*id为string类型时的get接口*/
    T get(final String id);

    T getActive(final String id);

    /*查询所有满足条件去重的结果*/
    List<T> getDistinct();

    /*查询所有满足条件去重并且有效的结果*/
    List<T> getDistinctActive();

    /* 查询所有满足条件且去重后的有效结果 */
    List<T> getDistinctActive(DBCriteriaBuilder crBuilder);

    /*查询所有满足条件且去重后的结果*/
    List<T> getDistinct(DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，未被删除的结果*/
    Page<T> getDistinctActivePage(Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，未被删除的结果*/
    Page<T> getActivePage(Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，未被删除的结果总行数*/
    int getDistinctActiveTotalNumber(DBCriteriaBuilder crBuilder);

    Long create(final T entity);

    T update(final T entity);

    T deactive(final T entity);

    T deactive(final Long id);

    T merge(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

    void deleteById(final String entityId);

    /*查询满足条件且去重后的页*/
    Page<T> getDistinctPage(Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询满足条件且未去重后的页*/
    Page<T> getPage(Page<T> page, DBCriteriaBuilder crBuilder);

    /*查询所有满足条件去重后，结果总行数*/
    int getDistinctTotalNumber(DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，未被删除的结果总行数*/
    int getActiveTotalNumber(DBCriteriaBuilder crBuilder);

    /*查询所有满足条件未去重后，结果总行数*/
    int getTotalNumber(DBCriteriaBuilder crBuilder);

    /* 下面两个方法是彻底删除, 即删除数据库中的相应表项 */
    void deleteForce(final T entity);

    void deleteByIdForce(final long entityId);

    /**
     * 批量删除操作
     * @param indexList:索引id list
     * @param entityName：待操作数据库entity
     */
    void deleteBulk(long[] indexList, String entityName);

    /**
     * 根据所有旧创建人，更新所有创建人ID
     * @param newCreatorId
     * @param entityName
     * @param conditions 满足条件的字段
     */
    void updateAllCreatorId(Long newCreatorId, String entityName, Map<String, String> conditions);


}
