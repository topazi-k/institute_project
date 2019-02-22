package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Classroom;

public class ClassroomDaoPostgreSql implements ClassroomDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Classroom create(Classroom classroom) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO classroom (number,capacity) VALUES(?,?);";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, classroom.getNumber());
            statement.setInt(2, classroom.getCapacity());
            
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            classroom.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if (!(statement == null)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
            if (!(connection == null)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
        }
        return classroom;
    }
    
    @Override
    public Classroom findById(int id) throws DaoException {
        Classroom classroom = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM classroom WHERE id=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            classroom = new Classroom();
            classroom.setNumber(resultSet.getInt("number"));
            classroom.setCapacity(resultSet.getInt("capacity"));
            classroom.setId(id);
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if (!(statement == null)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
            if (!(connection == null)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
        }
        return classroom;
    }
    
    @Override
    public List<Classroom> findAll() throws DaoException {
        Classroom classroom = null;
        List<Classroom> classrooms = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM classroom;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                classroom = new Classroom();
                classroom.setNumber(resultSet.getInt("number"));
                classroom.setCapacity(resultSet.getInt("capacity"));
                classroom.setId(resultSet.getInt("id"));
                classrooms.add(classroom);
            }
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if (!(statement == null)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
            if (!(connection == null)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
        }
        return classrooms;
    }
    
    @Override
    public Classroom updateInformation(Classroom classroom) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM classroom WHERE id=");
        sb.append(classroom.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateInt("number", classroom.getNumber());
            resultSet.updateInt("capacity", classroom.getCapacity());
            resultSet.updateRow();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            try {
                if (!(statement == null)) {
                    statement.close();
                }
                if (!(connection == null)) {
                    connection.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }
    
    @Override
    public void remove(Classroom classroom) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM classroom WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, classroom.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        } finally {
            if (!(statement == null)) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
            if (!(connection == null)) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new DaoException();
                }
            }
        } 
    }
    
}
