package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {

    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv= new ModelAndView("photo");
        return mv;
    }

}
