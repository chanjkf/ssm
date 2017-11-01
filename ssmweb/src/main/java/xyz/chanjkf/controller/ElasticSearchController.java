package xyz.chanjkf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.chanjkf.entity.UserEntity;
import xyz.chanjkf.service.IElasticSearchService;
import xyz.chanjkf.utils.DXPConst;
import xyz.chanjkf.utils.DXPException;
import xyz.chanjkf.utils.DXPLog;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.page.Page;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController("ElasticSearchController")
@RequestMapping("/search")
/**
 * Created by yi on 2017/9/12.
 */
public class ElasticSearchController {
    final static DXPLog dxpLog = new DXPLog (ElasticSearchController.class);

    @Resource(name = "ElasticSearchService")
    private IElasticSearchService elasticSearchService;

    /**
     * 初始化目录的搜索结构
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "init", method = RequestMethod.GET)
    public String initStructure(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("result", "success");

        try {
            elasticSearchService.initUserStructure();
        } catch (DXPException e) {
            dxpLog.error(e);
            map.put("result", e.getMessage());
        }

        return JsonUtil.getJsonStr(map);
    }


    /**
     *
     * @param request
     * @param response
     * @param searchKeys 查询关键字
     * @param searchType  查询类型 -- all或者空：查询所有；api：只查询api类型；searchType：查询非api类型
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView search(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "searchKeys", required = false) String searchKeys,
                               @RequestParam(value = "searchType", required = false) String searchType,
                               @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {

        ModelAndView mv = new ModelAndView("data-list-cat");
        if(null == pageNumber) {
            pageNumber = 1;
        }

        Page<UserEntity> page = new Page<UserEntity>(pageNumber, DXPConst.PAGE_SIZE);
        try {
            page = elasticSearchService.searchUser(page, searchKeys, searchType);
        } catch (DXPException e) {
            dxpLog.error(e);
        }


        mv.addObject("user_list", page.getResult());
        mv.addObject("pageNumber", pageNumber);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("searchKeys", searchKeys);
        mv.addObject("searchType", searchType);

        return mv;
    }
}