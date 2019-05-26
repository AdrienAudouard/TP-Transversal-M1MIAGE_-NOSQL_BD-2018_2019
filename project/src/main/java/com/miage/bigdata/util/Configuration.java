package com.miage.bigdata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration utility to read the configurations from properties file
 */
public class Configuration {
    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
    private static Properties prop = null;
    private String fileName;

    public static final Configuration COLUMN = new Configuration("column.properties");
    public static final Configuration DOCUMENT = new Configuration("document.properties");
    public static final Configuration KEY_VALUE = new Configuration("keyvalue.properties");
    public static final Configuration RELATIONAL = new Configuration("relational.properties");

    private Configuration(String fileName) {
        this.fileName = fileName;
    }

    private void loadProperties() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            LOGGER.error("Sorry, unable to find {}", fileName);
            return;
        }
        prop = new Properties();
        prop.load(input);
    }

    public String getProperty(String propertyName) throws IOException {
        if (prop == null) {
            loadProperties();
        }
        return prop.getProperty(propertyName);

    }
}
