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
        String sql = "INSERT INTO student (first_name,last_name,birth_day,\"group\") VALUES(?,?,?,null)";
        
        logger.debug("Creating student" + student + ". Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            logger.debug("Executing: INSERT INTO student " + student + ". Getting ResultSet");
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            student.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            logger.error("Can't create student with name " + student, e);
            throw new DaoException(e);
        }
        logger.info("INSERT INTO student " + student + "completed successfully");
        return student;
    }
    
    @Override
    public Student findById(int id) {
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        logger.debug("Finding student by id: " + id + "getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            logger.debug("Executing: SELECT * FROM student WHERE id=" + id + ". Getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            
            student = getStudentFromResultSet(resultSet);
            
        } catch (SQLException e) {
            logger.error("Can't find student id: " + id, e);
            throw new DaoException(e);
        }
        logger.trace("Return - " + student);
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        logger.debug("Finding all students. Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            logger.debug("Exequting: SELECT * FROM student. Getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                logger.trace("Setting student " + student + "to List students");
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
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE \"group\"=?";
        
        logger.debug(
                "Finding students by group with id -" + group.getId() + "Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, group.getId());
            logger.debug("Executing: SELECT * FROM student WHERE group=" + group.getId() + "Getting ResultSet");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                logger.trace("Setting student " + student + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            logger.error("Can't find students by group with id - " + group.getId(), e);
            throw new DaoException(e);
        }
        logger.trace("Return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public void update(Student student) {
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        logger.debug("Updating student  -" + student + " Getting Connection and creating Statement");
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            logger.debug("Executing:SELECT * FROM student WHERE id=" + student.getId() + ". Getting ResultSet");
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            logger.debug("Setting " + student + " to ResultSet and updating row");
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
            resultSet.updateRow();
            
        } catch (SQLException e) {
            logger.error("Can't update student - " + student, e);
            throw new DaoException(e);
        }
        logger.info("Student updated successfully - " + student);
    }
    
    @Override
    public void delete(Student student) {
        String sql = "DELETE FROM student WHERE id=?";
        logger.debug("Deleting student with id - " + student + "Getting Connection and PreparedStatiment");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, student.getId());
            logger.debug("Executing:DELETE FROM student WHERE id=" + student.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            logger.error("Can't delete student with id - " + student, e);
            throw new DaoException(e);
        }
        logger.info("Student - " + student + " deleted successfully");
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
