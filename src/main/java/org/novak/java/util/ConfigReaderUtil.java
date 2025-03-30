package org.novak.java.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderUtil {

    private static Properties configProperties = new Properties();
    private static final String CONFIG_PATH = "config.properties";

    static {
        try {
            configProperties.load(new FileInputStream(CONFIG_PATH));
        } catch (IOException ex) {
            throw new RuntimeException("Error loading config.properties", ex);
        }
    }

    public static String getProperty(String key) {
        return configProperties.getProperty(key);
    }
}