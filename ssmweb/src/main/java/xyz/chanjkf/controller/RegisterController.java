package xyz.chanjkf.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IRegisterService;
import xyz.chanjkf.service.IRoleService;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "RoleService")
    private IRoleService roleService;

    @Resource(name = "RegisterService")
    private IRegisterService registerService;


    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    public String toIndex(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "username", required = false) String name,
                          @RequestParam(value = "password", required = false) String password,
                          @RequestParam(value = "email", required = false) String email
                          ){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        UserEntity entity = userService.findUser(name);
        if (entity != null) {
            map.put("result", ExceptionType.ERROR_USER_EXIST.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        String path = request.getRequestURL().toString();
        path = path.substring(0, path.lastIndexOf("/"));
        path = path + "/activate";
        try {
            entity = registerService.registerUser(name, password, email, path);
        } catch (BaseException e) {
            map.put("result", e.getMessage());
            return JsonUtil.getJsonStr(map);
        }

        request.getSession().setAttribute("user",entity);
        return JsonUtil.getJsonStr(map);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView toPage(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("register");
        return mv;
    }

    @RequestMapping(value = "/checkName",method = RequestMethod.GET)
    @ResponseBody
    public String checkName(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "username", required = false) String name) {
        Map<String,Object> map = new HashMap<String,Object>();
        UserEntity entity = userService.findUser(name);
        if(entity != null){
            map.put("result","user already exists");
            return JsonUtil.getJsonStr(map);
        }
        map.put("result","success");
        return JsonUtil.getJsonStr(map);
    }

    @RequestMapping(value = "/activate",method = RequestMethod.GET)
    public void activateUser(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "validate", required = true) String validate,
                            @RequestParam(value = "user_id", required = true) Long user_id) {
        Map<String,Object> map = new HashMap<String,Object>(16);
        response.setContentType("text/html; charset=utf-8");
        map.put("result", Const.SUCCESS);

        String message = null;
        try {
            userService.activateUser(validate, user_id);
        } catch (BaseException e) {
            message = e.getMessage();
        }
        try {
            PrintWriter out = response.getWriter();

            if (message != null) {
                out.write(message);

            } else {
                out.write("ע��ɹ�,3�����ת����ҳ<a href='../index'>������ת</a>");
            }
            response.setHeader("refresh", "3;url=../index");
        } catch (IOException e) {

        }

    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    @ResponseBody
    public String toIndex(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "userId", required = false) Long userId){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        userService.deleteById(userId);
        return JsonUtil.getJsonStr(map);
    }
}
