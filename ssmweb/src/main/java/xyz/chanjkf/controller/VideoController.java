package xyz.chanjkf.controller;

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
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.VideoEntity;
import xyz.chanjkf.service.IVideoService;
import xyz.chanjkf.utils.DXPConst;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.RedisCacheUtil;
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
 * Created by yi on 2017/6/11.
 */
@Controller
@RequestMapping("/video")
public class VideoController {
    static ObjectMapper mapper = new ObjectMapper();

    @Resource(name = "VideoService")
    private IVideoService service;

    @Autowired
    private RedisCacheUtil<String> util;

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
        Page<VideoEntity> entityPage = null;
        try {
            String videoAddr = util.getCacheObject("movieAddr");
            if (videoAddr == null){
                entityPage = getMovieAddrFromDb(pageNumber,pageSize);
                String viedoEntity = mapper.writeValueAsString(entityPage);
                util.setCacheObject("movieAddr",viedoEntity);
            } else {
                entityPage = mapper.readValue(videoAddr,Page.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int totalPages = entityPage.getPageSize();
        List<VideoEntity> entityList = entityPage.getResult();
        mv.addObject("totalPages",totalPages);
        mv.addObject("pageNumber",pageNumber);
        mv.addObject("entityList",entityList);

        return mv;
    }
    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    private String toVideoPage(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("result","success");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session=request.getSession();
        session.setAttribute("progressBar",0);      //定义指定上传进度的Session变量
        int maxSize=100*1024*1024;        //单个上传文件大小的上限
        DiskFileItemFactory factory = new DiskFileItemFactory();        //基于磁盘文件项目创建一个工厂对象
        ServletFileUpload upload = new ServletFileUpload(factory);  //创建一个新的文件上传对象
        try {
            List items = upload.parseRequest(request);// 解析上传请求
            Iterator itr = items.iterator();// 枚举方法
            while (itr.hasNext()) {
                FileItem item = (FileItem) itr.next();  //获取FileItem对象
                if (!item.isFormField()) {// 判断是否为文件域
                    if (item.getName() != null && !item.getName().equals("")) {// 判断是否选择了文件
                        long upFileSize=item.getSize();     //上传文件的大小
                        String fileName=item.getName();     //获取文件名
                        String name = fileName.substring(fileName.lastIndexOf(".")+1);
                        if(!"mp4".equals(name)){
                            map.put("result","只支持MP4格式");
                        }
                        //System.out.println("上传文件的大小:" + item.getSize());
                        if(upFileSize>maxSize){
                            map.put("result","您上传的文件太大，请选择不超过100M的文件");
                            return JsonUtil.getJsonStr(map);
                        }
                        Long Id = service.getMaxIdFromDb();

                        String userIdStr = request.getSession().getAttribute(DXPConst.SESSION_USERID).toString();
                        int userId = Integer.parseInt(userIdStr);
                        fileName = "movie"+ userId+(Id+1)+".mp4";
                        // 此时文件暂存在服务器的内存中
                        File tempFile = new File(fileName);// 构造临时对象
                        String uploadPath = request.getServletContext().getRealPath("/video");
                        File file = new File(uploadPath,tempFile.getName());   // 获取根目录对应的真实物理路径
                        InputStream is=item.getInputStream();
                        int buffer=1024;     //定义缓冲区的大小
                        int length=0;
                        byte[] b=new byte[buffer];
                        double percent=0;
                        FileOutputStream fos=new FileOutputStream(file);
                        while((length=is.read(b))!=-1){
                            percent+=length/(double)upFileSize*100D;        //计算上传文件的百分比
                            fos.write(b,0,length);                      //向文件输出流写读取的数据
                            session.setAttribute("progressBar",Math.round(percent));    //将上传百分比保存到Session中
                        }
                        fos.close();
                        Thread.sleep(1000);     //线程休眠1秒
                    } else {
                        map.put("result","没有选择上传文件！");
                        return JsonUtil.getJsonStr(map);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","上传文件出现错误：" + e.getMessage());
            return JsonUtil.getJsonStr(map);
        }
        return JsonUtil.getJsonStr(map);
    }

    private Page<VideoEntity> getMovieAddrFromDb(String pageNumber,String pageSize){
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
        return entityPage;
    }
}
