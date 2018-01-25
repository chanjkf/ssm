package xyz.chanjkf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.entity.PhotoType;
import xyz.chanjkf.service.IAlbumService;
import xyz.chanjkf.service.IPhotoTypeService;
import xyz.chanjkf.utils.Const;
import xyz.chanjkf.utils.BaseTime;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.page.Page;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
@RequestMapping("/album")
public class PhotoController {

    @Resource(name = "AlbumService")
    IAlbumService albumService;

    @Resource(name = "PhotoTypeService")
    IPhotoTypeService photoTypeService;

    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize){
        if (pageNumber == null || pageNumber == 0) {
            pageNumber = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 9;
        }
        ModelAndView mv= new ModelAndView("photo");
        Page<AlbumEntity>page = new Page<>(pageNumber, pageSize);
        Page<AlbumEntity> albumpages = albumService.getPhotoData(page);
        List<AlbumEntity> result = albumpages.getResult();
        for (AlbumEntity album:result) {
            String url = album.getUrl();
        }
        mv.addObject("datas", result);
        mv.addObject("pageNumber", pageNumber);
        mv.addObject("totalPages", albumpages.getTotalPages());
        return mv;
    }











}
