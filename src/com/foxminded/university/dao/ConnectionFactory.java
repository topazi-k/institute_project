package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Properties properties;
    private static String driverName;
    private static String url;
    private static String user;
    private static String password;
    
    public Connection getConnection() {
        Connection connection = null;
        
        try {
            
            if (properties==null) {
                setProperties();
            }
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException | ClassNotFoundException e) {
            throw new DaoException(e);
        }
        return connection;
    }
    
    private static void setProperties() {
        
        properties = ConnectionJdbcProperties.getProperties();
        driverName = (String) properties.getProperty("driverName");
        url = (String) properties.getProperty("url");
        user = (String) properties.getProperty("userName");
        password = (String) properties.getProperty("password");
    }
}
