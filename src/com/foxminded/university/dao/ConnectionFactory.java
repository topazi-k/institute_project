package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory  {
    private String url = "jdbc:postgresql://localhost:5432/university";
    private String user = "new_user";
    private String password = "12345";
    
    public Connection getConnection() {
        Connection connection=null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
          
        }
        return connection;
    }
    
}
