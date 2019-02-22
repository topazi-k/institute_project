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
import com.foxminded.university.domain.Teacher;

public class TeacherDaoPostgreSql implements TeacherDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Teacher create(Teacher teacher) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO teacher (first_name,last_name,course,faculty) VALUES(?,?,?,null);";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            int courseId = teacher.getCourse().getId();
            statement.setObject(3, courseId);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            teacher.setId(resultSet.getInt("id"));
            
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
        return teacher;
    }
    
    @Override
    public Teacher findById(int id) throws DaoException {
        Teacher teacher = null;
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM teacher WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            teacher = new Teacher();
            teacher.setId(id);
            teacher.setFirstName(resultSet.getString("first_name"));
            teacher.setLastName(resultSet.getString("last_name"));
            
            int courseId = resultSet.getInt("course");
            Course course = new Course();
            if (courseId != 0) {
                course = courseDao.findById(courseId);
            }
            teacher.setCourse(course);
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
        return teacher;
    }
    
    @Override
    public List<Teacher> findAll() throws DaoException {
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM teacher;";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                int courseId = resultSet.getInt("course");
                Course course = new Course();
                if (courseId != 0) {
                    course = courseDao.findById(courseId);
                }
                teacher.setCourse(course);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new DaoException("");
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
        return teachers;
    }
    
    @Override
    public List<Teacher> findByFaculty(Faculty faculty) throws DaoException {
        Teacher teacher = null;
        List<Teacher> teachers = new ArrayList<>();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE faculty=");
        sb.append(faculty.getId());
        sb.append(";");
        String sql = sb.toString();
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher();
                teacher.setId(resultSet.getInt("id"));
                teacher.setFirstName(resultSet.getString("first_name"));
                teacher.setLastName(resultSet.getString("last_name"));
                int courseId = resultSet.getInt("course");
                Course course = new Course();
                if (courseId != 0) {
                    course = courseDao.findById(courseId);
                }
                teacher.setCourse(course);
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new DaoException("");
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
        return teachers;
    }
    
    @Override
    public void updateInformation(Teacher teacher) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE id=");
        sb.append(teacher.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateString("first_name", teacher.getFirstName());
            resultSet.updateString("last_name", teacher.getLastName());
            resultSet.updateInt("course", teacher.getCourse().getId());
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
    }
    
    @Override
    public void removeTeacher(Teacher teacher) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM teacher WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher.getId());
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
