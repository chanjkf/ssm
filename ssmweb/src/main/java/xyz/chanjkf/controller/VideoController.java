package xyz.chanjkf.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.IVideoService;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by yi on 2017/6/11.
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    static ObjectMapper mapper = new ObjectMapper();

    @Resource(name = "VideoService")
    private IVideoService videoService;

//    @Autowired
//    private RedisCacheUtil<String> util;


    @RequestMapping(value = "/page",method = RequestMethod.GET)
    private ModelAndView toVideoPage(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize){
        ModelAndView mv= new ModelAndView("video-center");
        if (pageNumber == null || pageNumber <= 0) {
            pageNumber = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 6;
        }
        Page<VideoEntity> entityPage = null;
//        try {
//            String videoAddr = util.getCacheObject("movieAddr");
//            if (videoAddr == null){
                entityPage = getMovieAddrFromDb(pageNumber,pageSize);
//                String viedoEntity = mapper.writeValueAsString(entityPage);
//                util.setCacheObject("movieAddr",viedoEntity);
//            } else {
//                entityPage = mapper.readValue(videoAddr,Page.class);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        int totalPages = entityPage.getTotalPages();
        List<VideoEntity> entityList = entityPage.getResult();
        mv.addObject("totalPages",totalPages);
        mv.addObject("pageNumber",pageNumber);
        mv.addObject("entityList",entityList);

        return mv;
    }
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    private ModelAndView videoDetail(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "id", required = false) Integer id){
        ModelAndView mv= new ModelAndView("video-detail");
        VideoEntity entity = videoService.getActive(id);
        videoService.increateViewCount(id);
        mv.addObject("data", entity);
        return mv;
    }


    private Page<VideoEntity> getMovieAddrFromDb(Integer pageNumber,Integer pageSize){

        Page<VideoEntity> entityPage = videoService.getVideoPage(pageNumber, pageSize);
        return entityPage;
    }
}
