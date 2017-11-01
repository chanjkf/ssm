package xyz.chanjkf.utils.exception;


import xyz.chanjkf.utils.ExceptionType;

/**
 * Created by yunwei0270 on 2015/8/11.
 */
public class DXPException extends Exception {

    public DXPException() {
    }

    public DXPException(String message) {
        super(message);
    }

    public DXPException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
}

    public DXPException(String message, Throwable cause) {
        super(message, cause);
    }

    public DXPException(ExceptionType exceptionType, Throwable cause) {
        super(exceptionType.getMessage(), cause);
    }

    public DXPException(Throwable cause) {
        super(cause);
    }

    public DXPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
