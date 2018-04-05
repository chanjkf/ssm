package xyz.chanjkf.utils;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by yi on 2018/3/20.
 */
public class PropertiesUtils {
    public static String username;
    public static String password;
    public static String serverHost;
    public static String serverPort;

    static {
        try {
            PropertiesConfiguration configuration = new PropertiesConfiguration("email.properties");
            username = configuration.getString("username");
            password = configuration.getString("password");
            PropertiesConfiguration security = new PropertiesConfiguration("persistence-security.properties");
            serverHost = security.getString("web.server.host");
            serverPort = security.getString("web.server.port");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }
}
