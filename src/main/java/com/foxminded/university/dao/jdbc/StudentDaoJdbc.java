package com.foxminded.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoJdbc implements StudentDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private static Logger log = LogManager.getLogger(StudentDaoJdbc.class);
    
    @Override
    public Student create(Student student) {
        log.debug("Creating student" + student);
        String sql = "INSERT INTO student (first_name, last_name, birth_day) VALUES(?,?,?)";
        
        log.debug("Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setObject(3, student.getBirthDay());
            log.debug("Executing: " + statement);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            student.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            log.error("Can't create student with name " + student, e);
            throw new DaoException(e);
        }
        log.info("Student with id: " + student.getId() + "created successfully");
        return student;
    }
    
    @Override
    public Student findById(int id) {
        log.debug("Finding student by id: " + id);
        Student student = null;
        String sql = "SELECT * FROM student WHERE id=?";
        
        log.debug("Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            log.debug("Executing: " + statement);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return student;
            }
            
            student = getStudentFromResultSet(resultSet);
            
        } catch (SQLException e) {
            log.error("Can't find student with id: " + id, e);
            throw new DaoException(e);
        }
        log.trace("Return student with id: " + student.getId());
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        log.debug("Finding all students");
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student";
        
        log.debug("Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            log.debug("Executing: " + statement);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                log.trace("Setting student with id: " + student.getId() + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            log.error("Can't find all students", e);
            throw new DaoException(e);
        }
        log.trace("Return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public List<Student> findByGroup(Group group) {
        log.debug("Finding students by group with id:" + group.getId());
        Student student = null;
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student WHERE \"group\"=?";
        
        log.debug("Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, group.getId());
            log.debug("Executing: " + statement);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                student = getStudentFromResultSet(resultSet);
                log.trace("Setting student: " + student + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            log.error("Can't find students by group with id - " + group.getId(), e);
            throw new DaoException(e);
        }
        log.trace("Return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public List<Student> findStudentsWithoutGroup() {
        log.debug("Finding students without group");
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student where (\"group\" is null)";
        
        log.debug("Getting Connection and PreparedStatement");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            log.debug("Executing: " + statement);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Student student = getStudentFromResultSet(resultSet);
                log.trace("Setting student: " + student + "to List students");
                students.add(student);
            }
            
        } catch (SQLException e) {
            log.error("Can't find students without group", e);
            throw new DaoException(e);
        }
        log.trace("Return studentsList. Size - " + students.size());
        return students;
    }
    
    @Override
    public void update(Student student) {
        log.debug("Updating  -" + student);
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE id=");
        sb.append(student.getId());
        String sql = sb.toString();
        
        log.debug("Getting Connection and creating Statement");
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            log.debug("Executing: " + statement);
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            log.debug("Setting with id: " + student.getId() + " to ResultSet and updating row");
            resultSet.updateString("first_name", student.getFirstName());
            resultSet.updateString("last_name", student.getLastName());
            resultSet.updateObject("birth_day", java.sql.Date.valueOf(student.getBirthDay()));
            resultSet.updateRow();
            
        } catch (SQLException e) {
            log.error("Can't update student - " + student, e);
            throw new DaoException(e);
        }
        log.trace("Student with id" + student.getId() + " updated successfully - ");
    }
    
    @Override
    public void delete(Student student) {
        log.debug("Deleting student with id: " + student.getId());
        String sql = "DELETE FROM student WHERE id=?";
        log.debug("Getting Connection and PreparedStatiment");
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, student.getId());
            log.debug("Executing: " + statement);
            statement.executeUpdate();
            
        } catch (SQLException e) {
            log.error("Can't delete student with id - " + student, e);
            throw new DaoException(e);
        }
        log.info("Student with id: " + student.getId() + " deleted successfully");
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
