package com.foxminded.university.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

import com.foxminded.university.constants.Constants;
import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

import java.util.List;
import java.util.ArrayList;

public class GroupDaoJdbc implements GroupDao {
    
    private ConnectionFactory connFactory = (ConnectionFactory) Constants.CONTEXT_SPRING.getBean("connectionFactory",ConnectionFactory.class);
    private StudentDaoJdbc studentDao = new StudentDaoJdbc();
    
    @Override
    public Group create(Group group) {
        String sql = "INSERT INTO \"group\" (number,name,faculty) VALUES(?,?,null)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, group.getGroupNumber());
            statement.setString(2, group.getGroupName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            group.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }
    
    @Override
    public Group findById(int groupId) {
        Group group = null;
        String sql = "SELECT * FROM \"group\" WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return group;
            }
            
            group = getGroupFromResultSet(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return group;
    }
    
    @Override
    public List<Group> findByFaculty(Faculty faculty) {
        Group group = null;
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM \"group\" WHERE faculty=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, faculty.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                group = getGroupFromResultSet(resultSet);
                groups.add(group);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return groups;
    }
    
    @Override
    public List<Group> findAll() {
        Group group = null;
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM \"group\"";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                group = getGroupFromResultSet(resultSet);
                groups.add(group);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return groups;
    }
    
    @Override
    public void update(Group group) {
        StringBuilder sb = new StringBuilder("SELECT * FROM \"group\" WHERE id=");
        sb.append(group.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("number", group.getGroupNumber());
            resultSet.updateString("name", group.getGroupName());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addStudent(Group group, Student student) {
        
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("group", group.getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeStudent(Student student) {
        
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("group", null);
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeAllStudents(Group group) {
        
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE \"group\"=");
        sb.append(group.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                resultSet.updateString("group", null);
                resultSet.updateRow();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Group group) {
        String sql = "DELETE FROM \"group\" WHERE id=?";
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, group.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Group getGroupFromResultSet(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("id"));
        group.setGroupName(resultSet.getString("name"));
        group.setGroupNumber(resultSet.getInt("number"));
        List<Student> students = studentDao.findByGroup(group);
        group.addStudents(students);
        
        return group;
    }
}
