package xyz.chanjkf.utils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 *
 * @author yi
 * @date 2017/12/21
 */
public class AddressUtil {
    public static String videoAddress;
    public static String photoAddress;
    static {
        PropertiesConfiguration configuration = null;
        try {
            configuration = new PropertiesConfiguration("storage_address.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        videoAddress = configuration.getString("video_address");
        photoAddress = configuration.getString("photo_address");
    }
}
