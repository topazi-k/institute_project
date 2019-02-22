package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;

public class CourseDaoPostgreSql implements CourseDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Course create(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO course (name,description,faculty) VALUES(?,?,null);";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            course.setId(resultSet.getInt("id"));
            
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
        return course;
    }
    
    @Override
    public Course findById(int id) throws DaoException {
        Course course = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM course WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            course = new Course();
            course.setId(id);
            course.setName(resultSet.getString("name"));
            course.setDescription(resultSet.getString("description"));
            
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
        return course;
    }
    
    @Override
    public List<Course> findAll() throws DaoException {
        Course course = null;
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM course;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                courses.add(course);
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
        return courses;
    }
    
    @Override
    public List<Course> findByFaculty(Faculty faculty) throws DaoException {
        Course course = null;
        List<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM course WHERE faculty=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, faculty.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                course = new Course();
                course.setId(resultSet.getInt("id"));
                course.setName(resultSet.getString("name"));
                course.setDescription(resultSet.getString("description"));
                courses.add(course);
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
        return courses;
    }
    
    @Override
    public void updateInformation(Course course) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM course WHERE id=");
        sb.append(course.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateString("name", course.getName());
            resultSet.updateString("description", course.getDescription());
            resultSet.updateRow();
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
    
    @Override
    public void removeCourse(Course course) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        
        String sql = "DELETE FROM course WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, course.getId());
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
