package xyz.chanjkf.utils;

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
}
