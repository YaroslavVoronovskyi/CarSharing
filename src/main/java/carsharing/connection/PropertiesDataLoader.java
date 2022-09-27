package carsharing.connection;

import carsharing.Constants;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesDataLoader {
    public String getProperty(String propertyName) throws IOException {
        String propertyValue;
        Properties properties = new Properties();
        try (FileInputStream inputStream = getFileFromResources(Constants.PROPERTIES)) {
            properties.load(inputStream);
            propertyValue = properties.getProperty(propertyName);
        } catch (IOException exception) {
            throw new IOException("Could not got property");
        }
        return propertyValue;
    }

    private FileInputStream getFileFromResources(String file) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(file);
        if (url == null) {
            throw new IllegalArgumentException("File not found");
        }
        return new FileInputStream(url.getFile());
    }
}
