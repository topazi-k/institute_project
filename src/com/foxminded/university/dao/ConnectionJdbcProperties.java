package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionJdbcProperties {
    
    private static Logger log = LogManager.getLogger(ConnectionJdbcProperties.class);
    private static final String PROPERTIES_FILE = "properties/connectionJdbc.properties";
    
    public static Properties getProperties() {
        Properties properties = new Properties();
        
        log.debug("Reading properties from file -" + PROPERTIES_FILE);
        try (FileInputStream fIS = new FileInputStream(PROPERTIES_FILE);) {
            
            properties.load(fIS);
            
        } catch (IOException e) {
            log.error("Can't get properties", e);
            throw new DaoException(e);
        }
        log.info("Properties for jdbc loaded successfully");
        return properties;
    }
}
