package xyz.chanjkf.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.AlbumEntity;
import xyz.chanjkf.service.IAlbumService;
import xyz.chanjkf.utils.DXPConst;
import xyz.chanjkf.utils.DXPTime;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.page.Page;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by yi on 2017/4/20.
 */
@Controller
@RequestMapping("/album")
public class PhotoController {

    @Resource(name = "AlbumService")
    IAlbumService albumService;

    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv= new ModelAndView("photo");

        return mv;
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getPhotoData(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "pageNum", required = false) Integer pageNum,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize){
        if (pageNum == 0) {
            pageNum = 1;
        }
        if (pageSize == 0) {
            pageSize = 9;
        }
        Page<AlbumEntity> page = new Page<>(pageNum, pageSize);
        Page<AlbumEntity> photoPage = albumService.getPhotoData(page);


        return "";
    }
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    private String toVideoPage(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "files") MultipartFile[] files,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "desc") String desc){

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        response.setContentType("text/html;charset=UTF-8");
        try {
            List<String> addrss = saveImage(request, files);
            AlbumEntity albumEntity = new AlbumEntity();
            albumEntity.setImgName(name);
            albumEntity.setDescription(desc);
            albumEntity.setUrl(addrss.get(0));
            albumService.create(albumEntity);

        } catch (IOException e) {
            e.getStackTrace();
            e.getMessage();
            map.put("result", "failed");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        return JsonUtil.getJsonStr(map);
    }

    private List<String> saveImage(HttpServletRequest request, MultipartFile[] files) throws IOException, InterruptedException {
        List<String>addrStrs = new ArrayList<>();
        for (int i= 0; i< files.length; i++) {
            MultipartFile multipartFile = files[i];

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
            String uploadPath = request.getServletContext().getRealPath("/photo");
            String fileAdd = DXPTime.formatDateTime(new Date(),"yyyyMMdd");
            File file1 = new File(uploadPath + File.separator + fileAdd);
            if (!file1.exists() && !file1 .isDirectory()){
                file1.mkdir();
            }
            File fileTraget = new File(uploadPath+ File.separator + fileAdd + File.separator + fileName);
            multipartFile.transferTo(fileTraget);
            addrStrs.add("photo" + File.separator + fileAdd + File.separator + fileName);
            Thread.sleep(520);     //线程休眠1秒

        }
        return addrStrs;
    }

}
