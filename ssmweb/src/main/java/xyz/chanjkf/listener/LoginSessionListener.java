package xyz.chanjkf.listener;

import xyz.chanjkf.dao.IUserDao;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.utils.Const;
import xyz.chanjkf.utils.ToolSpring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by yi on 2018/2/27.
 */
public class LoginSessionListener implements HttpSessionListener{

    private IUserService service;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sce) {
        if (service != null) {

        } else {
            synchronized (LoginSessionListener.class) {
                if (service == null) {
                    service = (IUserService)ToolSpring.getBean("UserService");
                }
            }
        }
        Long userId = (Long)sce.getSession().getAttribute(Const.SESSION_USERID);
        service.updateUserState(userId, false);
        if (userId != null) {
            ServletContext servletContext = sce.getSession().getServletContext();
            Object num = servletContext.getAttribute("online_num");
            if (num == null || (int)num <= 0) {

            } else {
                servletContext.setAttribute("online_num", ((int)num - 1));
            }
        }

    }
}
