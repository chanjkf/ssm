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
        UserEntity entity = null;
        try {
            entity = registerService.registerUser(name, password, email);
        } catch (DXPException e) {
            map.put("result", e.getMessage());
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
    @ResponseBody
    public String activateUser(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "validate", required = true) String validate,
                            @RequestParam(value = "user_id", required = true) Long user_id) {
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("result", DXPConst.SUCCESS);
        UserEntity entity = userService.getActive(user_id);
        if (entity == null) {
            map.put("result", ExceptionType.ERROR_ACTIVATE_USER.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        if (entity.isUseFlag()) {
            map.put("result", ExceptionType.ERROR_ACTIVATE_USER.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        if (!validate.equals(entity.getValidateCode())) {
            map.put("result", ExceptionType.ERROR_VALIDATE.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        Date create_Date = entity.getCreate_time();
        long create_time = create_Date.getTime();
        Date currentDate = new Date();
        Long currentTime = currentDate.getTime();
        long  between = currentTime - create_time;
        if(between > DXPConst.DAT_TIME){
            map.put("result", ExceptionType.ERROR_VALIDATE_OUTTIME.getMessage());
        }
        UserEntity entityNew = new UserEntity();
        try {
            DmallBeanUtils.copyProperties(entityNew, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        entityNew.setUseFlag(true);
        userService.update(entityNew);

        return JsonUtil.getJsonStr(map);
    }
}
