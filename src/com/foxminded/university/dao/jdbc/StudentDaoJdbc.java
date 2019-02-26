package com.foxminded.university.dao.jdbc;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class StudentDaoJdbc implements StudentDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Student create(Student student) {
        String sql = "INSERT INTO student (first_name,last_name,birth_day,\"group\") VALUES(?,?,?,null)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            student.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }
    
    @Override
    public Student findById(int id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            student = getStudentFromResultSet(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                students.add(student);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }
    
    @Override
    public List<Student> findByGroup(Group group) {
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE \"group\"=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, group.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                students.add(student);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return students;
    }
    
    @Override
    public void update(Student student) {
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
      
    }
    
    @Override
    public void delete(Student student) {
        String sql = "DELETE FROM student WHERE id=?";
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, student.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setBirthDay(resultSet.getObject("birth_day", LocalDate.class));
        
        return student;
    }
    
}
