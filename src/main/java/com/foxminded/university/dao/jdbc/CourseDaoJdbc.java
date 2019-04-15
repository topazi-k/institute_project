package com.foxminded.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;

public class CourseDaoJdbc implements CourseDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Course create(Course course) {
        String sql = "INSERT INTO course (name,description,faculty) VALUES(?,?,null)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            course.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return course;
    }
    
    @Override
    public Course findById(int id) {
        Course course = null;
        String sql = "SELECT * FROM course WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return course;
            }
            
            course = getCourseFromResultSet(resultSet);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return course;
    }
    
    @Override
    public List<Course> findAll() {
        Course course = null;
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                course = getCourseFromResultSet(resultSet);
                courses.add(course);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return courses;
    }
    
    @Override
    public List<Course> findByFaculty(Faculty faculty) {
        Course course = null;
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM course WHERE faculty=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, faculty.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                course = getCourseFromResultSet(resultSet);
                courses.add(course);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return courses;
    }
    
    @Override
    public void update(Course course) {
        StringBuilder sb = new StringBuilder("SELECT * FROM course WHERE id=");
        sb.append(course.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("name", course.getName());
            resultSet.updateString("description", course.getDescription());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Course course) {
        String sql = "DELETE FROM course WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, course.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Course getCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course();
        course.setId(resultSet.getInt("id"));
        course.setName(resultSet.getString("name"));
        course.setDescription(resultSet.getString("description"));
        return course;
    }
    
}
