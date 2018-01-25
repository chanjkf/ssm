package xyz.chanjkf.controller;

import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.chanjkf.model.WeatherModel;
import xyz.chanjkf.service.httpClent.RestService;
import xyz.chanjkf.service.httpClent.RestfulService;
import xyz.chanjkf.utils.Const;
import xyz.chanjkf.utils.HttpClientUtils;
import xyz.chanjkf.utils.JsonUtil;
import xyz.chanjkf.utils.exception.RestResponseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yi on 2018/1/18.
 */
@Controller
public class WeatherController {

    private String appcode = "b3ebe78c03fd46adabe9403ca2bf73ff";

    @Autowired
    private RestfulService restfulService;

    @RequestMapping(value = "/weather",method = RequestMethod.GET)
    @ResponseBody
    public String getWeather(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("result", "success");
        Long userId = (Long)request.getSession().getAttribute(Const.SESSION_USERID);
        String url = "http://jisutqybmf.market.alicloudapi.com/weather/query?city=";
        String str = null;
        try {
//            str = restfulService.get(url + "烟台", appcode);
            str = HttpClientUtils.getWeather(url + "烟台", appcode);
        } catch (Exception e) {
            map.put("result", "获取天气失败"+e.getMessage());
            return JsonUtil.getGsonStr(map);
        }
        if (str == null) {
            map.put("result", "获取天气失败");
            return JsonUtil.getGsonStr(map);
        }
        JSONObject jsonObject = JSONObject.fromObject(str);
        if (jsonObject == null) {
            map.put("result", "获取天气失败");
            return JsonUtil.getGsonStr(map);
        }
        if (jsonObject.get("status") == null) {
            map.put("result", "获取天气失败");
            return JsonUtil.getGsonStr(map);
        }
        if (!"0".equals(jsonObject.get("status").toString())) {
            map.put("result", "获取天气失败"+jsonObject.get("msg"));
            return JsonUtil.getGsonStr(map);
        }
        JSONObject result = jsonObject.getJSONObject("result");
        if (result == null) {
            map.put("result", "获取天气失败"+jsonObject.get("msg"));
            return JsonUtil.getGsonStr(map);
        }
        result.entrySet();
        Map<String, String> resultMap = result;
        WeatherModel model = transToModel(resultMap);

        if (model == null) {
            map.put("result", "获取天气失败");
            return JsonUtil.getGsonStr(map);
        } else {
            map.put("weather", model);
        }

        return JsonUtil.getGsonStr(map);
    }

    private WeatherModel transToModel(Map<String, String> resultMap) {
        WeatherModel model = new WeatherModel();
        model.setCity(resultMap.get("city"));
        model.setDate(resultMap.get("date"));
        model.setWeek(resultMap.get("week"));
        model.setWeather(resultMap.get("weather"));
        model.setTemp(resultMap.get("temp"));
        model.setTemphigh(resultMap.get("temphigh"));
        model.setTemplow(resultMap.get("templow"));
        return model;
    }
}
