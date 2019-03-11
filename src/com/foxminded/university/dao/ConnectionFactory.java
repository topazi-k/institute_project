package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
    private static Properties properties;
    private static Logger logger = LogManager.getLogger();
    
    public Connection getConnection() {
        logger.info("Gettig connection");
        Connection connection = null;
        
        try {
            
            logger.trace("checking properties on null");
            if (properties == null) {
                logger.debug("setting properties");
                setProperties();
            }
            logger.debug("finding jdbc driver");
            Class.forName((String) properties.getProperty("driverName"));
            logger.debug("getting connection from DriverManager");
            connection = DriverManager.getConnection((String) properties.getProperty("url"),
                    (String) properties.getProperty("userName"), (String) properties.getProperty("password"));
            
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("can't get connection",e);
            throw new DaoException(e);
        }
        return connection;
    }
    
    private static void setProperties() {
        
        properties = ConnectionJdbcProperties.getProperties();
    }
    
}
