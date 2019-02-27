package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionJdbcProperties {
    
    public static Properties getProperties()  {
        
        Properties properties = new Properties();
        
        try (FileInputStream fIS = new FileInputStream("properties/connectionJdbc.properties");) {
            
            properties.load(fIS);
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return properties;
    }
}
