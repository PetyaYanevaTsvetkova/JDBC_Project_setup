import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.System.setProperty;

public class PropertiesHelper {
    private static final String FILE_PATH = "src/main/resources/config.properties";

    private final String url;
    private final String user;
    private final String password;
    private Properties properties;

    private static PropertiesHelper helper;
    static {
        try {
            helper = new PropertiesHelper();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PropertiesHelper() throws IOException {
        this.properties = new Properties();
        FileReader fileReader = new FileReader(FILE_PATH);
        this.properties.load(fileReader);
        this.url = this.properties.getProperty("url");
        this.user = this.properties.getProperty("user");
        this.password = this.properties.getProperty("password");
    }

    public static PropertiesHelper getInstance() throws IOException {
        return helper;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }
}
