package xyz.chanjkf.utils.quarz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.web.context.ContextLoader;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.service.Impl.UserService;
import xyz.chanjkf.utils.Log;
import xyz.chanjkf.utils.ToolSpring;

import javax.servlet.ServletContext;
import javax.tools.Tool;

/**
 * Created by yi on 2017/7/31.
 */
public class CheckOnlineNum {
    private Log logger = new Log(CheckOnlineNum.class);
    private static IUserService userService = null;

    public void check() {
        if (userService == null) {
            userService = (IUserService)ToolSpring.getBean("UserService");
        }
        ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        if (servletContext != null) {
            Integer onlineNum = userService.getOnlineNum();
            servletContext.setAttribute("onlineNum", onlineNum);
        }
    }

}
