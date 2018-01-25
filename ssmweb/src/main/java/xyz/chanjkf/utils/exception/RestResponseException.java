package xyz.chanjkf.utils.exception;


import xyz.chanjkf.utils.ExceptionType;

/**
 * Created by yi
 */
public class RestResponseException extends BaseException {
    public RestResponseException(ExceptionType e){
        super(e.getMessage());
    }
    public RestResponseException(Class req, String message){
        super(req.getName() + "response" + message);
    }
}
