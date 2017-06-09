package xyz.chanjkf.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.chanjkf.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class LoginController {
    @RequestMapping("/doLogin")
    @ResponseBody
    public String toIndex(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value="username",required=true)String username,
                                @RequestParam(value="password",required=true)String password){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        if(request.getSession(false) ==null){
            map.put("result","false");
            JSONObject mapJson = JSONObject.fromObject(map);
            System.out.println(mapJson.toString());
            return mapJson.toString();
        }
        UserEntity user = (UserEntity) request.getSession(false).getAttribute("user");
        if(user == null){
            map.put("result","false");
            JSONObject mapJson = JSONObject.fromObject(map);
            return mapJson.toString();
        }
        map.put("user",user);
        JSONObject mapJson = JSONObject.fromObject(map);
        return mapJson.toString();
    }
}
