package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class FacultyDaoPostgreSql implements FacultyDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Faculty createFaculty(Faculty faculty) throws DaoException {
        
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO faculty(name) VALUES(?);";
        try {
            connection = connFactory.getConnection();
            try {
                stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, faculty.getName());
                stmt.executeUpdate();
                
                resultSet = stmt.getGeneratedKeys();
                resultSet.next();
                faculty = new Faculty();
                faculty.setId(resultSet.getInt("id"));
                faculty.setName(resultSet.getString("name"));
                
            } catch (SQLException e) {
                
            } finally {
                try {
                    resultSet.close();
                    stmt.close();
                } catch (Exception e) {
                    
                }
            }
        }
        
        catch (Exception e) {
            
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                
            }
        }
        
        return faculty;
    }
    
    @Override
    public Faculty findById(int id) throws DaoException {
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        String sql = "SELECT* FROM faculty WHERE id=?;";
        
        try {
            connection = connFactory.getConnection();
            try {
                stmt = connection.prepareStatement(sql);
                stmt.setInt(1, id);
                resultSet = stmt.executeQuery();
                resultSet.next();
                faculty = new Faculty();
                faculty.setId(resultSet.getInt("id"));
                faculty.setName(resultSet.getString("name"));
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            
        }
        return faculty;
    }
    
    @Override
    public Faculty updateInformation(Faculty faculty) {
        
        return faculty;
    }
    
    @Override
    public void addCourse(Course course, Faculty faculty) throws DaoException {
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
            resultSet=statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateInt("faculty", faculty.getId());
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
    public void removeCourse(Course course) throws DaoException{
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
            resultSet.updateString("faculty",null);
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
    public void addTeacher(Teacher teacher, Faculty faculty) throws DaoException{
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
            resultSet=statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateInt("faculty", faculty.getId());
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
    public void removeTeacher(Teacher teacher) throws DaoException{
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
            resultSet.updateString("faculty",null);
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
    public void addGroup(Group group, Faculty faculty) throws DaoException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM \"group\" WHERE id=");
        sb.append(group.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet=statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateInt("faculty", faculty.getId());
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
    public void removeGroup(Group group) throws DaoException{
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM \"group\" WHERE id=");
        sb.append(group.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateString("faculty",null);
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
    
}
