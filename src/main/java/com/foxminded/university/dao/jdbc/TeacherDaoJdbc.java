package com.foxminded.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Teacher;

public class TeacherDaoJdbc implements TeacherDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    private CourseDaoJdbc courseDao = new CourseDaoJdbc();
    
    @Override
    public Teacher create(Teacher teacher) {
        String sql = "INSERT INTO teacher (first_name,last_name,course,faculty) VALUES(?,?,?,null)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            int courseId = teacher.getCourse().getId();
            statement.setObject(3, courseId);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            teacher.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return teacher;
    }
    
    @Override
    public Teacher findById(int id) {
        Teacher teacher = null;
        String sql = "SELECT * FROM teacher WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            teacher = getTeacherFromResultSet(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return teacher;
    }
    
    @Override
    public List<Teacher> findAll() {
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teacher";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                teacher = getTeacherFromResultSet(resultSet);
                teachers.add(teacher);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return teachers;
    }
    
    @Override
    public List<Teacher> findByFaculty(Faculty faculty) {
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE faculty=");
        sb.append(faculty.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teacher = getTeacherFromResultSet(resultSet);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return teachers;
    }
    
    @Override
    public void update(Teacher teacher) {
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE id=");
        sb.append(teacher.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("first_name", teacher.getFirstName());
            resultSet.updateString("last_name", teacher.getLastName());
            resultSet.updateInt("course", teacher.getCourse().getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Teacher teacher) {
        String sql = "DELETE FROM teacher WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, teacher.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Teacher getTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("id"));
        teacher.setFirstName(resultSet.getString("first_name"));
        teacher.setLastName(resultSet.getString("last_name"));
        
        int courseId = resultSet.getInt("course");
        
        if (courseId != 0) {
            Course course = courseDao.findById(courseId);
            teacher.setCourse(course);
        }
        
        return teacher;
    }
}
