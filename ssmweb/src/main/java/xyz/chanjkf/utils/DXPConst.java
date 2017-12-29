package xyz.chanjkf.utils;

/**
 *
 * @author yi
 * @date 2017/6/8
 */
public class DXPConst {
    public static final int USERNAME_MAX = 32;
    public static final int NAME_MAX = 64;
    public static final int SUMMARY_MAX = 200;
    public static final int DESCRIBE_MAX = 1024;
    public static final int REASON_MAX = 2048;
    public static final int STRING_MAX = 2048;

    public static final int PAGE_SIZE = 10;

    public static final String SUCCESS = "success";

    /**
     * 索引库的别名
     */
    public final static String INDEX_ALIAS = "media";
    /**
     * es中的存储目录的type名称
     */
    public final static String USER_TABLE = "video";
    /**
     * Session信息
     */
    public static final String SESSION_USERID = "Id";
    public static final String SESSION_USERNAME = "User";

    public static final Long DAT_TIME = 24 * 3600000L;




}
