package xyz.chanjkf.utils;

/**
 * Created by yunwei0270 on 2015/8/25.
 */

/**
 * dxp 错误码、错误信息公共类
 */
public enum ExceptionType {
    ERROR_PARAMS_FORMAT(1, "参数不正确"),
    ERROR_PARAMS_LENGTH(2, "参数长度不正确"),
    ERROR_PARAMS_TYPE(3, "参数类型不正确"),
    ERROR_HTTP_PARSERESPONSE(70, "HTTP解析错误"),
    ERROR_IO_EXCEPTION(71, "IO异常"),
    ERROR_ILLEGAL_ARG_EXCEPTION(72, "非法参数异常"),
    ERROR_EXCEPTION(73, "内部异常"),
    ERROR_TYPE_CHANGE_EXCEPTION(74, "类型转换异常"),
    ERROR_TYPE_DATETIME(75, "时间转换异常"),
    ERROR_PROJECT_LIST(76, "项目列表获取异常"),
    ERROR_EXTERNAL_CALL(77, "外部接口调用错误"),
    ERROR_JSON_PARSER(78, "结果解析错误"),
    ERROR_OBJECT_CHANGE_EXCEPTION(79, "对象转换异常"),

    ERROR_ACTIVATE_USER(100, "对象转换异常"),
    ERROR_VALIDATE_OUTTIME(101, "校验码超时"),
    ERROR_VALIDATE_EXIST(102, "校验已通过"),
    ERROR_VALIDATE(150, "校验码错误");
    private int code;
    private String message;

    ExceptionType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "[Code:" + this.code + "; Message:" + this.message + "]";
    }

}
