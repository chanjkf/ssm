package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class MovieController {

    @RequestMapping("/player")
    public ModelAndView toplayer(
            @RequestParam(value="id",required=true)String id){
        ModelAndView mv = new ModelAndView("player");
        mv.addObject("movie","binke_"+id);
        return mv;
    }
}
