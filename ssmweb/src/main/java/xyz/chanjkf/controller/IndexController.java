package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.service.httpClent.IRestfulService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
public class IndexController {
    @Resource(name = "RestfulService")
    private IRestfulService restfulService;

    @RequestMapping("/index")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){
//        String result = testHttpClient();
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    private String testHttpClient() {
        String url = restfulService.getRequestUrl("code/industrys/tree");
        String result = restfulService.get(url,"123");
        return result;
    }
}
