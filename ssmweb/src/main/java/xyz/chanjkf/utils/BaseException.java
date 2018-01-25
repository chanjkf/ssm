package xyz.chanjkf.utils;

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
}
