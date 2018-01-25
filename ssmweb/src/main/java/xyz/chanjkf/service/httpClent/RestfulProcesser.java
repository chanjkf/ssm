package xyz.chanjkf.service.httpClent;


import xyz.chanjkf.utils.Log;
import xyz.chanjkf.utils.exception.RestResponseException;

import java.io.IOException;

/**
 * Created by yi on 2017/7/31.
 */
public abstract class RestfulProcesser {
    private static Log logger = new Log(RestfulProcesser.class);

    public abstract String doProcess() throws IOException, RestResponseException;

    public String process() {
        try {
            return doProcess();
        } catch (IOException e){
            logger.error("restful request : io exception", e);
        }catch (RestResponseException e) {
            logger.error("restful request : rest response exception", e);
        }
        return null;
    }
}
