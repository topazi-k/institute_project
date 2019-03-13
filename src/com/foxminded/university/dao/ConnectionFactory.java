package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
    private static Properties properties;
    private static Logger logger = LogManager.getLogger(ConnectionFactory.class);
    
    public Connection getConnection() {
        logger.debug("Gettig connection");
        Connection connection = null;
        
        try {
            
            logger.debug("Checking properties on null");
            if (properties == null) {
                setProperties();
            }
            logger.debug("Finding jdbc driver. Getting connection from DriverManager");
            Class.forName((String) properties.getProperty("driverName"));
            connection = DriverManager.getConnection((String) properties.getProperty("url"),
                    (String) properties.getProperty("userName"), (String) properties.getProperty("password"));
           
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Can't get connection", e);
            throw new DaoException(e);
        }
        logger.debug("Return connection");
        return connection;
    }
    
    private static void setProperties() {
        logger.debug("Setting properties");
        properties = ConnectionJdbcProperties.getProperties();
    }
    
}
