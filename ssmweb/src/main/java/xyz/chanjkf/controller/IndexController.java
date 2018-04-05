package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.service.httpClent.IRestfulService;
import xyz.chanjkf.utils.Const;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class IndexController {

    @Resource(name = "UserService")
    private IUserService userService;

    @RequestMapping("/index")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
//        String result = testHttpClient();
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping("/index/welcome")
    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @RequestMapping("/index/pre")
    public void preIndex(HttpServletRequest request, HttpServletResponse response){
        Long userId = (Long)request.getAttribute(Const.SESSION_USERID);
        userService.updateUserState(userId, true);
        UserEntity active = userService.getActive(userId);
        if (!active.isUseFlag()) {
            ServletContext servletContext = request.getServletContext();
            Object num = servletContext.getAttribute("online_num");
            if (num == null) {
                servletContext.setAttribute("online_num", 1);
            } else {
                servletContext.setAttribute("online_num", ((int)num + 1));
            }
        }

        try {
            request.getRequestDispatcher("/index").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
