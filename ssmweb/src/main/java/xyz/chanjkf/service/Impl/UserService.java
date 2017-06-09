package xyz.chanjkf.service.Impl;

import org.springframework.stereotype.Service;
import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.dao.common.IOperations;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.service.common.AbstractService;
import xyz.chanjkf.utils.page.CrParams;
import xyz.chanjkf.utils.page.DBCriteriaBuilder;
import xyz.chanjkf.utils.page.Parameter;

import javax.annotation.Resource;
import java.util.List;

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
}
