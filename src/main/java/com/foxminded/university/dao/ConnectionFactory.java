package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
    private static Properties properties;
    private static Logger log = LogManager.getLogger(ConnectionFactory.class);
    
    public Connection getConnection() {
        log.debug("Gettig connection");
        Connection connection = null;
        
        try {
            
            log.trace("Checking properties on null");
            if (properties == null) {
                setProperties();
            }
            log.debug("Finding jdbc driver");
            Class.forName((String) properties.getProperty("driverName"));
            log.debug(" Getting connection from DriverManager");
            connection = DriverManager.getConnection((String) properties.getProperty("url"),
                    (String) properties.getProperty("userName"), (String) properties.getProperty("password"));
            
        } catch (SQLException | ClassNotFoundException e) {
            log.error("Can't get connection", e);
            throw new DaoException(e);
        }
        log.debug("Return connection");
        return connection;
    }
    
    private static void setProperties() {
        log.debug("Setting properties");
        properties = ConnectionJdbcProperties.getProperties();
    }
    
}
