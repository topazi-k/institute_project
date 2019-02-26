package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    static Properties properties;
    static String driverName;
    static String url;
    static String user;
    static String password;
    
    public Connection getConnection() {
        Connection connection = null;
        
        try {
            
            if (driverName == null || url == null || user == null || password == null) {
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
