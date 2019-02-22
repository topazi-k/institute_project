package com.foxminded.university.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

import java.util.List;
import java.util.ArrayList;

public class GroupDaoPostgreSql implements GroupDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Group create(Group group) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO \"group\"(number,name,faculty) VALUES(?,?,null);";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, group.getGroupNumber());
            statement.setString(2, group.getGroupName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            group.setId(resultSet.getInt("id"));
            
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
        return group;
    }
    
    @Override
    public Group findById(int groupId) throws DaoException {
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM \"group\" WHERE id=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, groupId);
            resultSet = statement.executeQuery();
            resultSet.next();
            group = new Group();
            group.setId(groupId);
            group.setGroupName(resultSet.getString("name"));
            group.setGroupNumber(resultSet.getInt("number"));
            List<Student> students = new ArrayList<>();
            students = new StudentDaoPostgres().findByGroup(resultSet.getInt("id"));
            group.addStudents(students);
            
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
        return group;
    }
    
    @Override
    public List<Group> findByFaculty(int facultyId) throws DaoException {
        Group group = null;
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM \"group\" WHERE faculty=?";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, facultyId);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setGroupName(resultSet.getString("name"));
                group.setGroupNumber(resultSet.getInt("number"));
                List<Student> students = new ArrayList<>();
                students = new StudentDaoPostgres().findByGroup(resultSet.getInt("id"));
                group.addStudents(students);
                groups.add(group);
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
        return groups;
    }
    
    @Override
    public List<Group> findAll() throws DaoException {
        Group group = null;
        List<Group> groups = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM \"group\";";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setGroupName(resultSet.getString("name"));
                group.setGroupNumber(resultSet.getInt("number"));
                List<Student> students = new ArrayList<>();
                students = new StudentDaoPostgres().findByGroup(resultSet.getInt("id"));
                group.addStudents(students);
                groups.add(group);
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
        return groups;
    }
    
    @Override
    public Group updateInformation(Group group) throws DaoException {
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
            resultSet.updateInt("number", group.getGroupNumber());
            resultSet.updateString("name", group.getGroupName());
            resultSet.updateRow();
            
            group.setGroupNumber(resultSet.getInt("number"));
            group.setGroupName(resultSet.getString("name"));
            List<Student> students = new ArrayList<>();
            students = new StudentDaoPostgres().findByGroup(resultSet.getInt("id"));
            group.addStudents(students);
            
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
        return group;
    }
    
    public void addStudent(Group group, Student student) throws DaoException {
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
            resultSet.updateInt("group", group.getId());
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
    
    public void removeStudent(Student student) throws DaoException {
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
            resultSet.updateString("group",null);
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
    
    public void removeAllStudents(Group group) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM student WHERE group=");
        sb.append(group.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                resultSet.updateInt("group", 0);
                resultSet.updateRow();
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
    }
}
