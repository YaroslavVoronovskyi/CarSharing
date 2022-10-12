package carsharing.connection;

import carsharing.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesDataLoader {

    private static final Map<String, String> PROPERTIES_MAP = new HashMap<>();

    public static String getPropertyValue(String propertyName) {
        if (PROPERTIES_MAP.isEmpty()) {
            fillCache();
        }
        String propertyValue = PROPERTIES_MAP.get(propertyName);
        if (propertyValue != null) {
            return propertyValue;
        }
        throw new RuntimeException("Could not got property value");
    }

    private static void fillCache() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = getFileFromResources()) {
            properties.load(inputStream);
            for (String value : properties.stringPropertyNames()) {
                PROPERTIES_MAP.put(value, properties.getProperty(value));
            }
        } catch (IOException exception) {
            throw new RuntimeException("Could not got property");
        }
    }

    private static FileInputStream getFileFromResources() throws FileNotFoundException {
        ClassLoader classLoader = PropertiesDataLoader.class.getClassLoader();
        URL url = classLoader.getResource(Constants.PROPERTIES);
        if (url == null) {
            throw new IllegalArgumentException("File not found");
        }
        return new FileInputStream(url.getFile());
    }
}
