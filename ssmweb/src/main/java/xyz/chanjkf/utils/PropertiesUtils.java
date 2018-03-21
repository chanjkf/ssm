package xyz.chanjkf.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by yi on 2018/3/20.
 */
public class PropertiesUtils {
    public static String username;
    public static String password;

    static {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("email.properties");
            username = configuration.getString("username");
            password = configuration.getString("password");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
