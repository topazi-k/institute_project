package com.foxminded.university.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionJdbcProperties {
    
    private static Logger log = LogManager.getLogger(ConnectionJdbcProperties.class);
    private static final String PROPERTIES_FILE = "/connectionJdbc.properties";
    
    public static Properties getProperties() {
        Properties properties = new Properties();
        
        log.debug("Reading properties from file -" + PROPERTIES_FILE);
        try {
            
            InputStream propertiesStream = ConnectionJdbcProperties.class.getResourceAsStream(PROPERTIES_FILE);
            properties.load(propertiesStream);
            
        } catch (IOException e) {
            log.error("Can't get properties", e);
            throw new DaoException(e);
        }
        log.info("Properties for jdbc loaded successfully");
        return properties;
    }
}
