package com.miage.bigdata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration utility to read the configurations from properties file
 */
public enum Configuration {
    COLUMN("column.properties"),
    DOCUMENT("document.properties"),
    KEY_VALUE("keyvalue.properties"),
    RELATIONAL("relational.properties");

    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
    private Properties prop = null;
    private String fileName;

    Configuration(String fileName) {
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
