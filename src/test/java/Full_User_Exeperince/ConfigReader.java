package Full_User_Exeperince;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}