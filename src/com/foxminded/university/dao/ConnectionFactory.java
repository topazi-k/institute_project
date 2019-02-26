package com.foxminded.university.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    
    public Connection getConnection() {
        Connection connection = null;
        
        try {
            Properties properties = new ConnectionJdbcProperties().getProperties();
            String driverName = (String) properties.getProperty("driverName");
            String url = (String) properties.getProperty("url");
            String user = (String) properties.getProperty("userName");
            String password = (String) properties.getProperty("password");
            
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new DaoException(e);
        }
        return connection;
    }
    
}
