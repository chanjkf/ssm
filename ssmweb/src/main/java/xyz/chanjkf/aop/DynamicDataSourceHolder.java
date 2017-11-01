package xyz.chanjkf.aop;

/**
 * Created by yi on 2017/9/1.
 */
public class DynamicDataSourceHolder {
    //写库对应的数据源key
    private static final String MASTER = "master";
    //读库对应的数据源key
    private static final String SLAVE = "slave";

    private static final ThreadLocal<String>local = new ThreadLocal<String>();

    private static void saveDataBaseKey(String key) {
        local.set(key);
    }

    public static void setSlave(){
        saveDataBaseKey(SLAVE);
    }

    public static void setMaster() {
        saveDataBaseKey(MASTER);
    }

    public static String getKey() {
        return local.get();
    }
}
