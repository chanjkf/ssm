package xyz.chanjkf.controller;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
@RequestMapping("/toLogin")
public class LoginController {
    @RequestMapping("/page")
    @ResponseBody
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv= new ModelAndView("login");
        return mv;

    }
}
