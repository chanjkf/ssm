package xyz.chanjkf.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by yi on 18/1/11.
 */
public class CommonUtil {

    final static Log log = new Log(CommonUtil.class);

    /**
     * 启用elastic
     */
    public static final Integer USE_FLAG;


    static {
        int flag = Const.NOT_USE;
        try {
            /* 加载配置文件 */
            String path = CommonUtil.class.getResource("/").getPath() + "common.properties";
            path = URLDecoder.decode(path, "utf-8");
            Resource resource = new FileSystemResource(path);
            Properties configs = PropertiesLoaderUtils.loadProperties(resource);
            flag = Integer.valueOf(configs.getProperty("elastic_use"));
        } catch (Exception e) {
            log.error(e);
        }
        USE_FLAG = flag;
    }
}
