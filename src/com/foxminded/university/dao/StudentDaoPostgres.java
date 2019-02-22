package com.foxminded.university.dao;

import com.foxminded.university.domain.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class StudentDaoPostgres implements StudentDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Student createStudent(Student student) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO student (first_name,last_name,birth_day,\"group\") VALUES(?,?,?,null);";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            student.setId(resultSet.getInt("id"));
            
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
        return student;
    }
    
    @Override
    public Student findById(int id) throws DaoException {
        Student student = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM student WHERE id=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            student = new Student();
            student.setFirstName(resultSet.getString("first_name"));
            student.setLastName(resultSet.getString("last_name"));
            student.setBirthDay(resultSet.getObject("birth_day", LocalDate.class));
            student.setId(id);
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
        return student;
    }
    
    @Override
    public List<Student> findAll() throws DaoException {
        Student student = null;
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM student;";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setBirthDay(resultSet.getObject("birth_day", LocalDate.class));
                student.setId(resultSet.getInt("id"));
                
                students.add(student);
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
        return students;
    }
    
    @Override
    public List<Student> findByGroup(int groupId) throws DaoException {
        Student student = null;
        List<Student> students = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM student WHERE \"group\"=?;";
        
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student = new Student();
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setBirthDay(resultSet.getObject("birth_day", LocalDate.class));
                student.setId(resultSet.getInt("id"));
                students.add(student);
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
        return students;
    }
    
    @Override
    public Student updateInformation(Student student) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            resultSet.next();
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
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
        return student;
    }
    
    @Override
    public void removeStudent(Student student) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM student WHERE id=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, student.getId());
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
