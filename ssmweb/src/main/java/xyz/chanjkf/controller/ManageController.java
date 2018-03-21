package xyz.chanjkf.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import freemarker.ext.beans.HashAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.*;
import xyz.chanjkf.service.*;
import xyz.chanjkf.service.Impl.AlbumService;
import xyz.chanjkf.utils.*;
import xyz.chanjkf.utils.page.Page;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 *
 * @author YI
 * @date 2017/8/1
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @Resource(name = "PhotoTypeService")
    private IPhotoTypeService photoTypeService;

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "AlbumService")
    IAlbumService albumService;

    @Resource(name = "VideoService")
    private IVideoService videoService;

    @Resource(name = "RoleService")
    private IRoleService roleService;

    @Resource(name = "ElasticSearchService")
    private IElasticSearchService elasticService;

    @RequestMapping(value = "/album/upload",method = RequestMethod.POST)
    @ResponseBody
    private String toVideoPage(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "files", required = false) MultipartFile files,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "type", required = false) Long type,
                               @RequestParam(value = "desc", required = false) String desc){
        Map<String, Object> map = new HashMap<>(16);
        map.put("result", "success");
        Long id = (Long)request.getSession().getAttribute("Id");

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null;
        try {
            byte[] uploadBytes = files.getBytes();
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(QiNiuOSSUtil.accessKey, QiNiuOSSUtil.secretKey);
            String upToken = auth.uploadToken(QiNiuOSSUtil.bucket);
            try {
                Response response1 = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response1.bodyString(), DefaultPutRet.class);
                String qiNiuUrl = "http://p5xe9vmr2.bkt.clouddn.com/"+putRet.key;
                AlbumEntity albumEntity = new AlbumEntity();
                albumEntity.setImgName(name);
                albumEntity.setDescription(desc);
                albumEntity.setUrl(qiNiuUrl);
                albumEntity.setQiNiuKey(putRet.key);
                albumEntity.setCreator_id(id);
                PhotoType photoType = photoTypeService.getActive(type);
                albumEntity.setType(photoType);
                albumService.create(albumEntity);
            } catch (QiniuException ex) {
                Response r = ex.response;
                map.put("result", "上传失败："+r.bodyString());
                return JsonUtil.getJsonStr(map);
            }
        } catch (UnsupportedEncodingException ex) {
            map.put("result", "上传失败："+ex.getMessage());
            return JsonUtil.getJsonStr(map);
        } catch (IOException e) {
            map.put("result", "上传失败："+e.getMessage());
            return JsonUtil.getJsonStr(map);
        }

        return JsonUtil.getJsonStr(map);

    }

    @RequestMapping(value = "/album/type",method = RequestMethod.GET)
    @ResponseBody
    private ModelAndView toVideoPage(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize){
        ModelAndView mv = new ModelAndView("photo_type");
        Long id = (Long)request.getSession().getAttribute("Id");
        List<RoleEntity> roleByUser = roleService.getRoleByUser(id);
        boolean flag = false;
        for (RoleEntity role:roleByUser) {
            if ("ROLE_ADMIN".equals(role.getName())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            mv = new ModelAndView("access-forbidden");
            return mv;
        }
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 9;
        }

        Page<PhotoType> page = photoTypeService.getTypePage(pageNum, pageSize);
        mv.addObject("dataList", page.getResult());
        mv.addObject("pageNumber", pageNum);
        mv.addObject("totalPages", page.getTotalPages());
        return mv;
    }

    @RequestMapping(value = "/album/delete", method = RequestMethod.POST)
    private String deleteAlbum(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "id") Long id){
        Map<String, Object> map = new HashMap<>(6);
        AlbumEntity album = albumService.getActive(id);
        if (album == null) {
            map.put("result", "未找到该图片");
            return JsonUtil.getJsonStr(map);
        }
        String qiNiuKey = album.getQiNiuKey();
        try {
            albumService.deletePhoto(qiNiuKey);
        } catch (QiniuException ex) {
            map.put("result", "删除失败"+ex.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        albumService.deleteById(id);
        return JsonUtil.getJsonStr(map);
    }


    @RequestMapping(value = "/album",method = RequestMethod.GET)
    @ResponseBody
    private ModelAndView manageAblum(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "pageNumber", required = false) Integer pageNum,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize){
        ModelAndView mv = new ModelAndView("album_manage");
        Long id = (Long)request.getSession().getAttribute("Id");
        List<RoleEntity> roleByUser = roleService.getRoleByUser(id);
        boolean flag = false;
        for (RoleEntity role:roleByUser) {
            if ("ROLE_ADMIN".equals(role.getName())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            mv = new ModelAndView("access-forbidden");
            return mv;
        }
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        Page<AlbumEntity> page = new Page<>(pageNum,pageSize);
        Page<AlbumEntity> albumPages = albumService.getPhotoData(page);
        List<PhotoType> types = photoTypeService.getTypeList();
        mv.addObject("types", types);
        mv.addObject("album", albumPages.getResult());
        mv.addObject("totalPages",albumPages.getTotalPages());
        mv.addObject("pageNumber",pageNum);
        return mv;
    }

    @RequestMapping(value = "/album/type",method = RequestMethod.POST)
    @ResponseBody
    private String saveVideoType(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "type", required = false) String type){
        Map<String, Object> map = new HashMap<>(16);
        map.put("result","success");
        PhotoType photoType = new PhotoType();
        photoType.setTypeName(type);
        photoTypeService.create(photoType);
        return JsonUtil.getJsonStr(map);
    }
    @RequestMapping("/video")
    @ResponseBody
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "pageNumber", required = false) Integer pageNum,
                                @RequestParam(value = "pageSize", required = false) Integer pageSize){

        ModelAndView mv= new ModelAndView("video_manage");
        Long id = (Long)request.getSession().getAttribute("Id");
        List<RoleEntity> roleByUser = roleService.getRoleByUser(id);
        boolean flag = false;
        for (RoleEntity role:roleByUser) {
            if ("ROLE_ADMIN".equals(role.getName())) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            mv = new ModelAndView("access-forbidden");
            return mv;
        }
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        Page<VideoEntity> videoPage = videoService.getVideoPage(pageNum, pageSize);
        mv.addObject("videoLists", videoPage.getResult());
        mv.addObject("totalPages",videoPage.getTotalPages());
        mv.addObject("pageNumber",pageNum);
        return mv;
    }

    @RequestMapping(value = "/video/upload",method = RequestMethod.POST)
    @ResponseBody
    private void saveVideoPage(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "file", required = false) CommonsMultipartFile files,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "desc", required = false) String desc){
        Long id = (Long)request.getSession().getAttribute("Id");
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = "nc7Mv1scNTxq-BwLuNgQ-tmWwXWHvCLc7RF-1GAw";
        String secretKey = "H5twB2EYhEx2waDMk8pJ3O0KrxcwFZsq27sNZnv1";
        String bucket = "chanjkf";
        String key = null;
        byte[] uploadBytes = files.getBytes();
        ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response1 = uploadManager.put(byteInputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response1.bodyString(), DefaultPutRet.class);
            String qiNiuUrl = "http://p5xe9vmr2.bkt.clouddn.com/"+putRet.key;
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setName(name);
            videoEntity.setDescription(desc);
            videoEntity.setAddress(qiNiuUrl);
            videoEntity.setCreator_id(id);
            videoService.create(videoEntity);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }
    @RequestMapping(value = "/video/delete", method = RequestMethod.POST)
    private String deleteVideo(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "id") Long id){
        Map<String, Object> map = new HashMap<>(6);
        VideoEntity video = videoService.getActive(id);
        if (video == null) {
            map.put("result", "未找到该图片");
            return JsonUtil.getJsonStr(map);
        }
        String qiNiuKey = video.getQiNiuKey();
        try {
            videoService.deletePhoto(qiNiuKey);
        } catch (QiniuException ex) {
            map.put("result", "删除失败"+ex.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        videoService.deleteById(id);
        return JsonUtil.getJsonStr(map);

    }

    @RequestMapping(value = "/online/num", method = RequestMethod.GET)
    @ResponseBody
    public String getOnlineNum (HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>(10);
        ServletContext servletContext = request.getServletContext();
        int onlineNum = (int)servletContext.getAttribute("online_num");
        map.put("result", "success");
        map.put("online", onlineNum);
        return JsonUtil.getJsonStr(map);
    }

    @RequestMapping(value = "/online/users", method = RequestMethod.GET)
    @ResponseBody
    public String getOnlineUsers (HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "pageNumber", required = false) Integer pageNum,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize){
        Map<String, Object> map = new HashMap<>(10);
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }
        Page<UserEntity> userPages = userService.getUserPages(pageNum, pageSize, true);
        map.put("userList", userPages.getResult());
        map.put("totalPages",userPages.getTotalPages());
        map.put("pageNumber",pageNum);
        map.put("result", "success");
        return JsonUtil.getJsonStr(map);
    }
}
