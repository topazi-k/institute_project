package com.foxminded.university.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionJdbcProperties {
   
    public Properties getProperties() throws IOException {
        FileInputStream fIS = new FileInputStream("properties/connectionJdbc.properties");
        Properties properties = new Properties();
        properties.load(fIS);
        
        return properties;
    }
}
