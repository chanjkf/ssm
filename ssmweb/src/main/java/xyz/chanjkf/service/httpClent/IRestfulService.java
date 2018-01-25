package xyz.chanjkf.service.httpClent;

import javax.annotation.PostConstruct;

/**
 * Created by yi on 2017/9/20.
 */
public interface IRestfulService {

    String getIpMsg(String ip);

    String get(String actionUrl, String appCode);

    String post(String requestUrl, String jsonBody, String userName);

    String put(String requestUrl, String jsonBody, String userName);

    String delete(String requestUrl, String resourceNo, String userName);


}
