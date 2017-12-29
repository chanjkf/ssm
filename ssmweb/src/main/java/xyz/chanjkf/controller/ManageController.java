package xyz.chanjkf.controller;

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
import xyz.chanjkf.entity.RoleEntity;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.*;
import xyz.chanjkf.service.Impl.RoleService;
import xyz.chanjkf.utils.AddressUtil;
import xyz.chanjkf.utils.DXPConst;
import xyz.chanjkf.utils.DXPTime;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
        try {
            List<String> addrss = saveImage(request, files);
            AlbumEntity albumEntity = new AlbumEntity();
            albumEntity.setImgName(name);
            albumEntity.setDescription(desc);
            albumEntity.setUrl(addrss.get(0));
            albumEntity.setCreator_id(id);
            PhotoType photoType = photoTypeService.getActive(type);
            albumEntity.setType(photoType);
            albumService.create(albumEntity);

        } catch (IOException e) {
            e.getStackTrace();
            e.getMessage();
            map.put("result", "上传失败"+e.getMessage());
            return JsonUtil.getJsonStr(map);
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("result", "上传失败"+e.getMessage());
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
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        PrintWriter out;
        try {
            boolean flag = saveVideo(request, files, name, desc);
            out = response.getWriter();
            if (true == flag) {
                out.print("1");
            } else {
                out.print("2");
            }
        } catch (IOException e) {
            e.getStackTrace();
            e.getMessage();
            map.put("result", "上传失败"+e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            map.put("result", "上传失败"+e.getMessage());
        }
    }
    private boolean saveVideo(HttpServletRequest request, CommonsMultipartFile  files, String name, String desc) throws IOException, InterruptedException {
        MultipartFile multipartFile = files;

        multipartFile.getName();
        String originalFilename = multipartFile.getOriginalFilename();
        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));

        Long Id = videoService.getMaxIdFromDb();

        String userIdStr = request.getSession().getAttribute(DXPConst.SESSION_USERID).toString();
        Long userId = Long.parseLong(userIdStr);
        Long time = System.currentTimeMillis();
        String fileName = "video"+ userId+(Id+1)+time+originalFilename;
        // 此时文件暂存在服务器的内存中
//                File tempFile = new File(fileName);// 构造临时对象
        String uploadPath = AddressUtil.videoAddress;
        String fileAdd = DXPTime.formatDateTime(new Date(),"yyyyMMdd");
        File file1 = new File(uploadPath + File.separator + fileAdd);
        if (!file1.exists() && !file1 .isDirectory()){
            file1.mkdir();
        }
        File fileTarget = new File(uploadPath+ File.separator + fileAdd + File.separator + fileName);
        String address = "/videos" + "/" + fileAdd + "/" + fileName;
        multipartFile.transferTo(fileTarget);
        VideoEntity videoEntity = new VideoEntity();
        videoEntity.setName(name);
        videoEntity.setDescription(desc);
        videoEntity.setAddress(address);
        videoEntity.setCreator_id(userId);
        videoService.create(videoEntity);
        //保存到es
        elasticService.saveVideo(videoEntity);
        return true;
    }
    private List<String> saveImage(HttpServletRequest request, MultipartFile files) throws IOException, InterruptedException {
        List<String>addrStrs = new ArrayList<>();
//        for (int i= 0; i< files.length; i++) {
        MultipartFile multipartFile = files;

        multipartFile.getName();
        String originalFilename = multipartFile.getOriginalFilename();
        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));

        Long Id = albumService.getMaxIdFromDb();

        String userIdStr = request.getSession().getAttribute(DXPConst.SESSION_USERID).toString();
        int userId = Integer.parseInt(userIdStr);
        Long time = System.currentTimeMillis();
        String fileName = "photo"+ userId+(Id+1)+time+originalFilename;
        // 此时文件暂存在服务器的内存中
//                File tempFile = new File(fileName);// 构造临时对象

        String uploadPath = AddressUtil.photoAddress;
        String fileAdd = DXPTime.formatDateTime(new Date(),"yyyyMMdd");
        File file1 = new File(uploadPath + File.separator + fileAdd);
        if (!file1.exists() && !file1.isDirectory()){
            file1.mkdir();
        }
        File fileTarget = new File(uploadPath+ File.separator + fileAdd + File.separator + fileName);
        multipartFile.transferTo(fileTarget);
        addrStrs.add("/images" + "/" + fileAdd + "/" + fileName);
//        }
        return addrStrs;
    }
}
