package xyz.chanjkf.utils.exception;


import xyz.chanjkf.utils.ExceptionType;

/**
 * Created by yunwei0270 on 2015/8/15.
 */
public class RestResponseException extends DXPException {
    public RestResponseException(ExceptionType e){
        super(e.getMessage());
    }
    public RestResponseException(Class req, String message){
        super(req.getName() + "response" + message);
    }
}
