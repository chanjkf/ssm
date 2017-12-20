package xyz.chanjkf.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.PhotoType;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.IVideoService;
import xyz.chanjkf.utils.DXPConst;
import xyz.chanjkf.utils.DXPTime;
import xyz.chanjkf.utils.JsonUtil;
//import xyz.chanjkf.utils.RedisCacheUtil;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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



    private Page<VideoEntity> getMovieAddrFromDb(Integer pageNumber,Integer pageSize){

        Page<VideoEntity> entityPage = videoService.getVideoPage(pageNumber, pageSize);
        return entityPage;
    }
}
