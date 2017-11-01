package xyz.chanjkf.service.httpClent;


import xyz.chanjkf.utils.DXPLog;
import xyz.chanjkf.utils.exception.RestResponseException;

import java.io.IOException;

/**
 * Created by z0253 on 2016/2/23.
 */
public abstract class RestfulProcesser {
    private static DXPLog logger = new DXPLog(RestfulProcesser.class);

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
