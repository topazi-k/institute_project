package com.foxminded.university.dao.jdbc;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class StudentDaoJdbc implements StudentDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private static Logger logger = LogManager.getLogger();
    
    @Override
    public Student create(Student student) {
        logger.info("Creating student");
        String sql = "INSERT INTO student (first_name,last_name,birth_day,\"group\") VALUES(?,?,?,null)";
        
        logger.debug("trying to get Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            logger.trace("setting data to PreparedStatement");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            
            logger.debug("executing update");
            statement.executeUpdate();
            logger.debug("trying to get ResultSet");
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            student.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            logger.error("can't create student");
            throw new DaoException(e);
        }
        logger.trace("return student");
        return student;
    }
    
    @Override
    public Student findById(int id) {
        logger.info("Finding student by id: " + id);
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        logger.debug("trying to get Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.trace("setting student id to PreparedStatement");
            statement.setInt(1, id);
            logger.debug("trying to get ResultSet");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            logger.debug("Getting student from ResultSet");
            student = getStudentFromResultSet(resultSet);
            
        } catch (SQLException e) {
            logger.error("Can't find student id: " + id, e);
            throw new DaoException(e);
        }
        logger.trace("return student");
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        logger.info("Finding all students");
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        logger.debug("trying to get Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.debug("trying to get ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            logger.debug("getting students from ResultSet");
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                students.add(student);
            }
            
        } catch (SQLException e) {
            logger.error("Can't find all students", e);
            throw new DaoException(e);
        }
        logger.trace("Return all students");
        return students;
    }
    
    @Override
    public List<Student> findByGroup(Group group) {
        logger.info("Finding students by group id -"+group.getId());
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE \"group\"=?";
        
        logger.debug("trying to get Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.trace("setting group id to PreparedStatement");
            statement.setInt(1, group.getId());
            logger.debug("trying to get ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            logger.debug("getting students from ResultSet");
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                students.add(student);
            }
            
        } catch (SQLException e) {
            logger.error("Can't find students by group", e);
            throw new DaoException(e);
        }
        logger.trace("return students");
        return students;
    }
    
    @Override
    public void update(Student student) {
        logger.info("Updating student with id -"+student.getId());
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        logger.debug("trying to get Connection and create Statement");
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            logger.debug("trying to execute query and get ResultSet");
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            logger.debug("setting data to ResultSet and updateRow");
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
            resultSet.updateRow();
            
        } catch (SQLException e) {
            logger.error("can't update student",e);
            throw new DaoException(e);
        }
        
    }
    
    @Override
    public void delete(Student student) {
        logger.info("Deleting student");
        String sql = "DELETE FROM student WHERE id=?";
        logger.debug("trying to get Connection and PreparedStatiment");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.trace("setting student id to PreparedStatement");
            statement.setInt(1, student.getId());
            logger.debug("executing update");
            statement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("can't delete student",e);
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
