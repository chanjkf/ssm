package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public ModelAndView toIndex(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
}
