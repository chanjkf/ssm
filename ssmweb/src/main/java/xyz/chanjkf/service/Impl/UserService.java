package xyz.chanjkf.service.Impl;

import com.sun.jna.platform.win32.Netapi32Util;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.*;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Parameter;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by yi on 2017/6/7.
 */
@Service("UserService")
public class UserService extends AbstractService<UserEntity> implements IUserService {

    @Resource(name = "UserDao")
    private IUserDao dao;

    @Override
    protected IOperations<UserEntity> getDao() {
        return dao;
    }

    @Override
    public UserEntity findUser(String userName) {

        try {
            DBCriteriaBuilder builder = new DBCriteriaBuilder();
            CrParams params = new CrParams("UserEntity");
            Parameter parameter = new Parameter("userName",userName);
            params.getParamList().add(parameter);
            builder.getFilterParams().add(params);
            List<UserEntity> userList = getDistinctActive(builder);
            if(userList == null || userList.size()==0){
                return null;
            }
            return userList.get(0);

        } catch (Exception e) {
            e.getMessage();
            e.getStackTrace();
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long findMaxId() {
        String sql = "select max(id) from user ";
        Long id = (Long) getCurrentSession().createSQLQuery(sql).uniqueResult();
        return id;
    }

    @Override
    public void activateUser(String validate, Long user_id) throws BaseException {
        UserEntity entity = this.getActive(user_id);
        if (entity == null) {
            throw new BaseException(ExceptionType.ERROR_ACTIVATE_USER);
        }
        if (entity.isUseFlag()) {
            throw new BaseException(ExceptionType.ERROR_VALIDATE_EXIST.getMessage());
        }
        if (!validate.equals(entity.getValidateCode())) {
            throw new BaseException(ExceptionType.ERROR_VALIDATE.getMessage());
        }
        Date create_Date = entity.getCreate_time();
        long create_time = create_Date.getTime();
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        long  between = currentTime - create_time;
        if(between > Const.DAT_TIME){
            throw new BaseException(ExceptionType.ERROR_VALIDATE_OUTTIME.getMessage());
        }
        getCurrentSession().createQuery("update user set use_flag=1 where id="+user_id).executeUpdate();
    }

    public UserEntity findUserByNameAndPass(String username, String password) {
        UserEntity user = findUser(username);
        if (password == null || !password.equals(user.getUserPassword())) {
            return null;
        }
        return user;
    }
}
