package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Properties properties;
    
    public Connection getConnection() {
        Connection connection = null;
        
        try {
            
            if (properties == null) {
                setProperties();
            }
            Class.forName((String) properties.getProperty("driverName"));
            connection = DriverManager.getConnection((String) properties.getProperty("url"),
                    (String) properties.getProperty("userName"), (String) properties.getProperty("password"));
            
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException(e);
        }
        return connection;
    }
    
    private static void setProperties() {
        
        properties = ConnectionJdbcProperties.getProperties();
    }
    
}
