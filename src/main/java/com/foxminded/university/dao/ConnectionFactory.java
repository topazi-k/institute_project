package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;

import com.foxminded.university.constants.Constants;

public class ConnectionFactory {
    
    private static Logger log = LogManager.getLogger(ConnectionFactory.class);
    private static DataSource dataSource;
    
    public Connection getConnection() {
        
        log.debug("Getting connection");
        Connection connection;
        try {
            log.trace("Checking DataSource on null");
            if (dataSource == null) {
                
                log.debug("Getting DataSource from ApplicationContext(Spring)");
                dataSource = (DataSource) Constants.CONTEXT_SPRING.getBean("dataSource", DataSource.class);
            }
            log.debug(" Getting connection from DataSource");
            connection = dataSource.getConnection();
            
        } catch (SQLException | BeansException e) {
            log.error("Can't get connection", e);
            throw new DaoException(e);
        }
        log.debug("Return connection");
        return connection;
    }
    
}
