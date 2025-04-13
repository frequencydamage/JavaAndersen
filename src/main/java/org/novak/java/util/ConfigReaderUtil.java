package org.novak.java.util;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class ConfigReaderUtil {

    private Properties configProperties = new Properties();
    private static final String CONFIG_PATH = "config.properties";

    public ConfigReaderUtil() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_PATH)) {
            if (inputStream == null) {
                throw new RuntimeException("config.properties not found in resources");
            }
            configProperties.load(inputStream);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    public String getProperty(String key) {
        return configProperties.getProperty(key);
    }
}