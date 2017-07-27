package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.IVideoService;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by yi on 2017/6/11.
 */
@Controller
@RequestMapping("/video")
public class VideoController {

    @Resource(name = "VideoService")
    private IVideoService service;

    @RequestMapping(value = "/address",method = RequestMethod.GET)
    @ResponseBody
    private String writeAddress(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        String path = request.getSession().getServletContext().getRealPath("");
        String videoPath = path+"/video";
        File f = new File(videoPath);
        File fa[] = f.listFiles();
        List<VideoEntity> videoList = service.getDistinctActive();
        List<String> nameList = new ArrayList<String>();
        for (VideoEntity en : videoList) {
            nameList.add(en.getName());
        }
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {

            } else {
                String name = fs.getName();
                String fileName = name.substring(name.lastIndexOf("/")+1);
                if(!nameList.contains(fileName)){
                    VideoEntity entity = new VideoEntity();
                    entity.setName(fileName);
                    String str = request.getRequestURL().toString();
                    String webPath = str.substring(0,str.indexOf("/",12))+"/video/"+fileName;
                    entity.setAddress(webPath);
                    entity.setViewCount(0);
                    service.create(entity);
                }
            }
        }

        return JsonUtil.getJsonStr(map);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    private ModelAndView toVideoPage(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "pageNumber", required = false) String pageNumber,
                                     @RequestParam(value = "pageSize", required = false) String pageSize){
        ModelAndView mv= new ModelAndView("video-center");
        int pageNum = 0;
        int size = 0;
        try {
            pageNum = Integer.parseInt(pageNumber);
        }catch (Exception e){
            pageNum = 0;
        }
        try {
            size = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            size = 6;
        }
        Page<VideoEntity> entityPage = service.getVideoPage(pageNum,size);
        int totalPages = entityPage.getPageSize();
        List<VideoEntity> entityList = entityPage.getResult();
        mv.addObject("totalPages",totalPages);
        mv.addObject("pageNumber",pageNum);
        mv.addObject("entityList",entityList);

        return mv;
    }
}
