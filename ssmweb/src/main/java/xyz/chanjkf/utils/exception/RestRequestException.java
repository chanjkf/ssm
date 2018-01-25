package xyz.chanjkf.utils.exception;

/**
 * Created by yi on 2017/7/31.
 */
public class RestRequestException extends BaseException {
    public RestRequestException(){}
    public RestRequestException(Class req, String message){
        super(req.getName() + "request" + message);
    }
}
