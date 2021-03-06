package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionFactory {
    
    private static Logger log = LogManager.getLogger(ConnectionFactory.class);
    private static DataSource dataSource;
    private static EntityManagerFactory emf;
    private static ApplicationContext context = new ClassPathXmlApplicationContext("appContextSpring.xml");
    
    public Connection getConnection() {
        
        log.debug("Getting connection");
        Connection connection;
        try {
            log.trace("Checking DataSource on null");
            if (dataSource == null) {
                log.debug("Creating ApplicationContext(spring)");
                log.debug("Getting DataSource from ApplicationContext(Spring)");
                dataSource = (DataSource) context.getBean("dataSource", DataSource.class);
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
    
    public EntityManager getEntityManager() {
        EntityManager em;
        try {
            if (emf == null) {
                emf = (EntityManagerFactory) context.getBean("myEmf");
            }
            em = emf.createEntityManager();
        } catch (BeansException | IllegalStateException e) {
            throw new DaoException(e);
        }
        return em;
    }
}
