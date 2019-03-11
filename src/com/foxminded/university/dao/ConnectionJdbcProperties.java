package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionJdbcProperties {
    private static Logger logger = LogManager.getLogger();
    
    public static Properties getProperties()  {
       logger.info("Getting properties for jdbc connection");
        Properties properties = new Properties();
        
        logger.debug("trying to read properties from file - properties/connectionJdbc.properties");
        try (FileInputStream fIS = new FileInputStream("properties/connectionJdbc.properties");) {
            
            logger.trace("loading properties to Properties object");
            properties.load(fIS);
        } catch (IOException e) {
            logger.error("can't get properties",e);
            throw new DaoException(e);
        }
        logger.trace("return properties");
        return properties;
    }
}
