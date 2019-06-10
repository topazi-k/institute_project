package com.foxminded.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.constants.Constants;
import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.domain.Classroom;

public class ClassroomDaoJdbc implements ClassroomDao {
    
    private ConnectionFactory connFactory = (ConnectionFactory) Constants.CONTEXT_SPRING.getBean("connectionFactory",ConnectionFactory.class);
    
    @Override
    public Classroom create(Classroom classroom) {
        String sql = "INSERT INTO classroom (number,capacity) VALUES(?,?)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, classroom.getNumber());
            statement.setInt(2, classroom.getCapacity());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            classroom.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return classroom;
    }
    
    @Override
    public Classroom findById(int id) {
        Classroom classroom = null;
        String sql = "SELECT * FROM classroom WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return classroom;
            }
            
            classroom = getClassroomFromResultSet(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return classroom;
    }
    
    @Override
    public List<Classroom> findAll() {
        Classroom classroom = null;
        List<Classroom> classrooms = new ArrayList<>();
        String sql = "SELECT * FROM classroom";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                classroom = getClassroomFromResultSet(resultSet);
                classrooms.add(classroom);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return classrooms;
    }
    
    @Override
    public void update(Classroom classroom) {
        StringBuilder sb = new StringBuilder("SELECT * FROM classroom WHERE id=");
        sb.append(classroom.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("number", classroom.getNumber());
            resultSet.updateInt("capacity", classroom.getCapacity());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Classroom classroom) {
        String sql = "DELETE FROM classroom WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, classroom.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Classroom getClassroomFromResultSet(ResultSet resultSet) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setNumber(resultSet.getInt("number"));
        classroom.setCapacity(resultSet.getInt("capacity"));
        classroom.setId(resultSet.getInt("id"));
        
        return classroom;
    }
    
}
