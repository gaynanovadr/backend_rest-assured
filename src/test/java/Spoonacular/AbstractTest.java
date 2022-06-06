package Spoonacular;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String apikey;
    private static String baseUrl;

    @BeforeAll
    static void initTest() throws IOException {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);
        apikey=prop.getProperty("apikey");
        baseUrl= prop.getProperty("baseUrl");
    }

    public static String getApikey() {
        return apikey;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
