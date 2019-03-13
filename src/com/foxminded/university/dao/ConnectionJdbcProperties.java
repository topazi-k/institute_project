package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionJdbcProperties {
    private static Logger logger = LogManager.getLogger(ConnectionJdbcProperties.class);
    
    public static Properties getProperties() {
        Properties properties = new Properties();
        
        logger.debug("Reading properties from file - properties/connectionJdbc.properties");
        try (FileInputStream fIS = new FileInputStream("properties/connectionJdbc.properties");) {
            
            properties.load(fIS);
            
        } catch (IOException e) {
            logger.error("Can't get properties", e);
            throw new DaoException(e);
        }
        logger.info("Properties for jdbc loaded successfully");
        return properties;
    }
}
