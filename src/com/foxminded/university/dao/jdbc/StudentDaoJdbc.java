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
    private static Logger logger = LogManager.getLogger(StudentDaoJdbc.class);
    
    @Override
    public Student create(Student student) {
        logger.debug("Creating student " + student.toString());
        String sql = "INSERT INTO student (first_name,last_name,birth_day,\"group\") VALUES(?,?,?,null)";
        
        logger.debug("getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            logger.trace("setting data to PreparedStatement");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            
            logger.debug("executing update");
            statement.executeUpdate();
            logger.debug("getting ResultSet");
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            student.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            logger.error("can't create student with name " + student.getFirstName() + " " + student.getLastName(), e);
            throw new DaoException(e);
        }
        logger.info("created new student id - " + student.toString());
        return student;
    }
    
    @Override
    public Student findById(int id) {
        logger.debug("Finding student by id: " + id);
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        logger.debug("getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.trace("setting student id to PreparedStatement");
            statement.setInt(1, id);
            logger.debug("getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            logger.debug("Getting student with id - " + id + " from ResultSet");
            student = getStudentFromResultSet(resultSet);
            
        } catch (SQLException e) {
            logger.error("Can't find student id: " + id, e);
            throw new DaoException(e);
        }
        logger.trace("return student - " + student.toString());
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        logger.debug("Finding all students");
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        logger.debug("getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.debug("getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            logger.debug("getting students from ResultSet");
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                logger.trace("setting student with id - " + student.getId() + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            logger.error("Can't find all students", e);
            throw new DaoException(e);
        }
        logger.trace("Return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public List<Student> findByGroup(Group group) {
        logger.debug("Finding students by group with id -" + group.getId());
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE \"group\"=?";
        
        logger.debug("getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.trace("setting group id to PreparedStatement");
            statement.setInt(1, group.getId());
            logger.debug("getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            logger.debug("getting students from ResultSet");
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                logger.trace("setting student with id - " + student.getId() + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            logger.error("Can't find students by group with id - " + group.getId(), e);
            throw new DaoException(e);
        }
        logger.trace("return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public void update(Student student) {
        logger.debug("Updating student  -" + student.toString());
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        logger.debug("getting Connection and create Statement");
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            logger.debug("executing query and getting ResultSet");
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            logger.debug("setting data to ResultSet and updateRow");
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
            resultSet.updateRow();
            
        } catch (SQLException e) {
            logger.error("can't update student - " + student.toString(), e);
            throw new DaoException(e);
        }
        logger.info("student updated successfully - " + student.toString());
    }
    
    @Override
    public void delete(Student student) {
        logger.debug("Deleting student with id - " + student.toString());
        String sql = "DELETE FROM student WHERE id=?";
        logger.debug("getting Connection and PreparedStatiment");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.trace("setting student id to PreparedStatement");
            statement.setInt(1, student.getId());
            logger.debug("executing deletion student with id - " + student.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("can't delete student with id - " + student.toString(), e);
            throw new DaoException(e);
        }
        logger.info("Student - " + student.toString() + " deleted successfully");
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
