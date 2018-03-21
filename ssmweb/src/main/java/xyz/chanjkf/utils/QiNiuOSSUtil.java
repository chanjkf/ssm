package xyz.chanjkf.utils;

import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

/**
 * Created by yi on 2018/3/21.
 */
public class QiNiuOSSUtil {
    public static final String accessKey = "nc7Mv1scNTxq-BwLuNgQ-tmWwXWHvCLc7RF-1GAw";
    public static final String secretKey = "H5twB2EYhEx2waDMk8pJ3O0KrxcwFZsq27sNZnv1";
    public static final String bucket = "chanjkf";

    private static Configuration cfg = null;

    public static Configuration getConfiguration() {
        if (cfg == null) {
            synchronized (QiNiuOSSUtil.class) {
                if (cfg == null) {
                    cfg = new Configuration(Zone.zone2());
                }
            }
        }
        return cfg;
    }
}
