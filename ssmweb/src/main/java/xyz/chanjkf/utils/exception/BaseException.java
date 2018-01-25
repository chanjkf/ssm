package xyz.chanjkf.utils.exception;


import xyz.chanjkf.utils.ExceptionType;

/**
 * Created by yi on 2017/7/31.
 */
public class BaseException extends Exception {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
}

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(ExceptionType exceptionType, Throwable cause) {
        super(exceptionType.getMessage(), cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
