package xyz.chanjkf.dao.common;

import com.google.common.base.Preconditions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import xyz.chanjkf.entity.common.BaseEntity;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Page;
import xyz.chanjkf.utils.page.Parameter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends BaseEntity> implements IOperations<T> {

    private Class<T> clazz;

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    public AbstractHibernateDao() {
    }

    public AbstractHibernateDao(Class<T> clazz) {
        this.clazz = Preconditions.checkNotNull(clazz);
    }

    @Override
    /*获取上下文的当前事务会话*/
    public final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public final T get(final long id) {
        Session curSession = getCurrentSession();
        T entity = (T) curSession.get(clazz, id);

        return entity;
    }

    @Override
    public final T getActive(final long id) {
        T entity = get(id);
        if(null != entity && entity.isActive_flag()) {
            return entity;
        }

        return null;
    }

    @Override
    public final T get(final String id) {
        Session curSession = getCurrentSession();
        T entity = (T) curSession.get(clazz, id);

        return entity;
    }

    @Override
    public final T getActive(final String id) {
        T entity = get(id);
        if (null != entity && entity.isActive_flag()) {
            return entity;
        }

        return null;
    }

    @Override
    /*查询所有满足条件去重的结果*/
    public final List<T> getDistinct() {
        List<T> tmpList;

        Session curSession = getCurrentSession();
        tmpList = curSession.createQuery("from " + clazz.getName()).list();

        return tmpList;
    }

    @Override
    /*查询所有满足条件去重并且有效的结果*/
    public final List<T> getDistinctActive() {
        List<T> tmpList;

        Session curSession = getCurrentSession();
        tmpList = curSession.createQuery("from " + clazz.getName() + " where active_flag=" + true).list();

        return tmpList;
    }

    @Override
    /*查询所有满足条件且去重后的有效结果*/
    public final List<T> getDistinctActive(DBCriteriaBuilder crBuilder) {

        Parameter parameter = new Parameter("active_flag", true);
        crBuilder.getFilterParams().get(0).addParam(parameter);

        return getDistinct(crBuilder);
    }

    @Override
    /*查询所有满足条件且去重后的结果*/
    public final List<T> getDistinct(DBCriteriaBuilder crBuilder) {
        List<T> result = new ArrayList<T>();

        /*去重查询结果*/
        crBuilder.setDistinct(true);
        Session curSession = getCurrentSession();
        Criteria cr = crBuilder.buildAll(curSession, clazz);
        if (null != cr) {
            result = cr.list();
        }

        return result;
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果*/
    public Page<T> getDistinctActivePage(Page<T> page, DBCriteriaBuilder crBuilder) {

        Parameter parameter = new Parameter("active_flag", true);
        crBuilder.getFilterParams().get(0).addParam(parameter);
        crBuilder.setDistinct(true);

        return getDistinctPage(page, crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果*/
    public Page<T> getActivePage(Page<T> page, DBCriteriaBuilder crBuilder) {

        Parameter parameter = new Parameter("active_flag", true);
        crBuilder.getFilterParams().get(0).addParam(parameter);
        crBuilder.setDistinct(false);

        return getPage(page, crBuilder);
    }

    @Override
    /*查询所有满足条件去重后，未被删除的结果总行数*/
    public int getDistinctActiveTotalNumber(DBCriteriaBuilder crBuilder) {
        crBuilder.getFilterParams().get(0).addParam(new Parameter("active_flag", true));
        return getDistinctTotalNumber(crBuilder);
    }

    @Override
    /*查询所有满足条件未去重后，未被删除的结果总行数*/
    public int getActiveTotalNumber(DBCriteriaBuilder crBuilder) {
        crBuilder.getFilterParams().get(0).addParam(new Parameter("active_flag", true));
        return getTotalNumber(crBuilder);
    }

    @Override
    /*查询满足条件且去重后的页*/
    public Page<T> getDistinctPage(Page<T> page, DBCriteriaBuilder crBuilder) {
        List<T> result = new ArrayList<T>();
        crBuilder.setDistinct(true);

        Session curSession = getCurrentSession();
        Criteria cr = crBuilder.buildOnePage(curSession, clazz, page.getStartRow(), page.getPageSize());
        if (null != cr){
            result = cr.list();
        }
        page.setResult(result);

        int totalPages, totalRows;
        totalRows = crBuilder.getDistinctTotalRows(curSession, clazz);

        if (totalRows % page.getPageSize() == 0) {
            totalPages = totalRows / page.getPageSize();
        } else {
            totalPages = totalRows / page.getPageSize() + 1;
        }

        page.setTotalPages(totalPages);
        page.setTotalRows(totalRows);


        return page;
    }

    @Override
    /*查询满足条件且未去重后的页*/
    public Page<T> getPage(Page<T> page, DBCriteriaBuilder crBuilder) {
        List<T> result = new ArrayList<T>();
        crBuilder.setDistinct(false);

        Session curSession = getCurrentSession();
        Criteria cr = crBuilder.buildOnePage(curSession, clazz, page.getStartRow(), page.getPageSize());
        if (null != cr) {
            result = cr.list();
        }
        page.setResult(result);

        int totalPages, totalRows;
        totalRows = crBuilder.getTotalRows(curSession, clazz);

        if (totalRows % page.getPageSize() == 0)
            totalPages = totalRows / page.getPageSize();
        else
            totalPages = totalRows / page.getPageSize() + 1;

        page.setTotalPages(totalPages);
        page.setTotalRows(totalRows);

        return page;
    }

    @Override
    /*查询所有满足条件去重后，结果总行数*/
    public int getDistinctTotalNumber(DBCriteriaBuilder crBuilder) {
        int count = 0;

        crBuilder.setDistinct(true);
        Session curSession = getCurrentSession();
        count = crBuilder.getDistinctTotalRows(curSession, clazz);

        return count;
    }

    @Override
    /*查询所有满足条件未去重后，结果总行数*/
    public int getTotalNumber(DBCriteriaBuilder crBuilder) {
        int count = 0;
        crBuilder.setDistinct(true);
        Session curSession = getCurrentSession();
        count = crBuilder.getTotalRows(curSession, clazz);

        return count;
    }

    @Override
    public final T update(final T entity) {
        /* 设置更新时间 */
        entity.setUpdate_time(new Date());

        Session curSession = getCurrentSession();
        curSession.update(entity);

        return entity;
    }

    @Override
    public final T deactive(final T entity) {
        if (true == entity.isActive_flag()) {
            entity.setActive_flag(false);
            update(entity);
        }

        return entity;
    }

    @Override
    public final T deactive(final Long id) {
        T entity = null;

        Session curSession = getCurrentSession();
        entity = (T) curSession.get(clazz, id);
        entity.setActive_flag(false);

        return entity;
    }

    @Override
    public final Long create(final T entity) {
        /* 设置时间和删除标记 */
        Date time = new Date();
        entity.setCreate_time(time);
        entity.setUpdate_time(time);
        entity.setActive_flag(true);

        Session curSession = getCurrentSession();
        return (Long) curSession.save(entity);
    }

    @Override
    public final T merge(final T entity) {
        /* 设置更新时间 */
        entity.setUpdate_time(new Date());

        Session curSession = getCurrentSession();
        curSession.merge(entity);


        return entity;
    }

    @Override
    public final void delete(final T entity) {
        /* 设置删除标记 */
        entity.setActive_flag(false);
        merge(entity);

        return;
    }

    @Override
    public void deleteForce(T entity){

        Session curSession = getCurrentSession();
        curSession.delete(entity);

        return;
    }

    @Override
    public final void deleteById(final long entityId) {
        final T entity = get(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);

        return;
    }

    @Override
    public final void deleteById(final String entityId) {
        final T entity = get(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);

        return;
    }

    @Override
    public void deleteByIdForce(long entityId) {
        final T entity = get(entityId);
        Preconditions.checkState(entity != null);
        deleteForce(entity);

        return;
    }

    /**
     * 批量删除操作
     * @param indexList:索引id list
     * @param entityName：待操作数据库表名
     */
    @Override
    public void deleteBulk(long[] indexList, String entityName) {

        /*带操作的数据库表名*/
        if (null == entityName) {
            return;
        }

        //数组中封装的是ID的集合;
        if (0 == indexList.length) {
            return;
        }

        String hql = "";
        for (int i = 0; i < indexList.length; i++) {
            if (i == 0) {
                hql = "id=" + indexList[i];
            } else {
                hql = hql + " or id=" + indexList[i];
            }
        }

        Session session = getCurrentSession();
        String str = "update " + entityName + " SET active_flag = 0 where " + hql;
        org.hibernate.Query q = session.createQuery(str);
        q.executeUpdate();

        return;
    }

    /**
     * 根据所有旧创建人，更新所有创建人ID
     * @param newCreatorId
     * @param entityName
     * @param conditions 满足条件的字段
     */
    @Override
    public void updateAllCreatorId(Long newCreatorId, String entityName, Map<String, String> conditions) {
        Session session = getCurrentSession();
        StringBuilder hql = new StringBuilder();
        hql.append("update ").append(entityName).append(" set creator_id = ").append(newCreatorId);

        if (!conditions.isEmpty()) {
            StringBuilder where = new StringBuilder(" where ");
            for (Map.Entry<String, String> entry : conditions.entrySet()) {
                where.append(entry.getKey()).append(" = '").append(entry.getValue()).append("' and ");
            }

            int startIndex = where.lastIndexOf(" and ");
            int lastIndex = startIndex + " and ".length() - 1;
            where.replace(startIndex, lastIndex, "");
            hql.append(where);
        }


        org.hibernate.Query q = session.createQuery(hql.toString());
        q.executeUpdate();
    }
}
