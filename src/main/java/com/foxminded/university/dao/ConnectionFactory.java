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
    private static Context context;
    private static DataSource dataSource;
    
    public Connection getConnection() {
        log.debug("Gettig connection");
        Connection connection;
        
        try {
            log.trace("Checking DataSource on null");
            if (dataSource == null) {
                context = new InitialContext();
                log.debug("Getting DataSource from InitialContext");
                dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/UniversityDB");
            }
            log.debug(" Getting connection from DataSource");
            connection = dataSource.getConnection();
            
        } catch (SQLException | NamingException e) {
            log.error("Can't get connection", e);
            throw new DaoException(e);
        }
        log.debug("Return connection");
        return connection;
    }
    
}
