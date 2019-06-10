package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionFactory {
    
    private static Logger log = LogManager.getLogger(ConnectionFactory.class);
    private static DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Connection getConnection() {
        log.debug("Getting connection");
        Connection connection;
        
        try {
            
            log.debug(" Getting connection from DataSource");
            connection = dataSource.getConnection();
            
        } catch (SQLException e) {
            log.error("Can't get connection", e);
            throw new DaoException(e);
        }
        log.debug("Return connection");
        return connection;
    }
    
}
