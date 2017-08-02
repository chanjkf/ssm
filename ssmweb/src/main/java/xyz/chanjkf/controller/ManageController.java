package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YI on 2017/8/1.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private ModelAndView toVideoPage(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new ModelAndView("manageupload");

        return mv;
    }
}
