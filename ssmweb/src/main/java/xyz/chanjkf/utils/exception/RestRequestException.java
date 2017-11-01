package xyz.chanjkf.utils.exception;

/**
 * Created by yunwei0270 on 2015/8/15.
 */
public class RestRequestException extends DXPException {
    public RestRequestException(){}
    public RestRequestException(Class req, String message){
        super(req.getName() + "request" + message);
    }
}
