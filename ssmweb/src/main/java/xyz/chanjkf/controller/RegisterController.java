package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IRegisterService;
import xyz.chanjkf.service.IRoleService;
import xyz.chanjkf.service.IUserService;
import xyz.chanjkf.utils.JsonUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class RegisterController {

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "RoleService")
    private IRoleService roleService;

    @Resource(name = "RegisterService")
    private IRegisterService registerService;


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public String toIndex(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "username", required = false) String name,
                          @RequestParam(value = "password", required = false) String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");

        UserEntity entity = registerService.registerUser(name,password);
        request.getSession().setAttribute("user",entity);

        return JsonUtil.getJsonStr(map);
    }
}
