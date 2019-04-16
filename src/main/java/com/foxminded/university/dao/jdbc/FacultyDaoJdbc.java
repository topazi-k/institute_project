package com.foxminded.university.dao.jdbc;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class FacultyDaoJdbc implements FacultyDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private TeacherDaoJdbc teacherDao = new TeacherDaoJdbc();
    private GroupDaoJdbc groupDao = new GroupDaoJdbc();
    private CourseDaoJdbc courseDao = new CourseDaoJdbc();
    
    @Override
    public Faculty create(Faculty faculty) {
        String sql = "INSERT INTO faculty(name) VALUES(?)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, faculty.getName());
            stmt.executeUpdate();
            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.next();
            
            faculty.setId(resultSet.getInt("id"));
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return faculty;
    }
    
    @Override
    public Faculty findById(int id) {
        Faculty faculty = null;
        String sql = "SELECT* FROM faculty WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return faculty;
            }
            
            faculty = getFacultyFromResultSet(resultSet);
            
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return faculty;
    }
    
    @Override
    public List<Faculty> findAll() {
        Faculty faculty = null;
        List<Faculty> faculties = new ArrayList<>();
        String sql = "SELECT * FROM faculty";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                faculty = getFacultyFromResultSet(resultSet);
                faculties.add(faculty);
            }
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return faculties;
    }
    
    @Override
    public void update(Faculty faculty) {
        StringBuilder sb = new StringBuilder("SELECT * FROM faculty WHERE id=");
        sb.append(faculty.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("name", faculty.getName());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addCourse(Course course, Faculty faculty) {
        StringBuilder sb = new StringBuilder("SELECT * FROM course WHERE id=");
        sb.append(course.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("faculty", faculty.getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    public void removeCourse(Course course) {
        StringBuilder sb = new StringBuilder("SELECT * FROM course WHERE id=");
        sb.append(course.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("faculty", null);
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException();
        }
    }
    
    @Override
    public void addTeacher(Teacher teacher, Faculty faculty) {
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE id=");
        sb.append(teacher.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("faculty", faculty.getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeTeacher(Teacher teacher) {
        StringBuilder sb = new StringBuilder("SELECT * FROM teacher WHERE id=");
        sb.append(teacher.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("faculty", null);
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addGroup(Group group, Faculty faculty) {
        StringBuilder sb = new StringBuilder("SELECT * FROM \"group\" WHERE id=");
        sb.append(group.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateInt("faculty", faculty.getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeGroup(Group group) {
        StringBuilder sb = new StringBuilder("SELECT * FROM \"group\" WHERE id=");
        sb.append(group.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE)) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            
            resultSet.updateString("faculty", null);
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Faculty faculty) {
        String sql = "DELETE FROM faculty WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, faculty.getId());
            statement.executeUpdate();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Faculty getFacultyFromResultSet(ResultSet resultSet) throws SQLException {
        Faculty faculty = new Faculty();
        faculty.setId(resultSet.getInt("id"));
        faculty.setName(resultSet.getString("name"));
        List<Teacher> teachers = teacherDao.findByFaculty(faculty);
        List<Group> groups = groupDao.findByFaculty(faculty);
        List<Course> courses = courseDao.findByFaculty(faculty);
        faculty.addTeachers(teachers);
        faculty.addGroups(groups);
        faculty.addCourses(courses);
        
        return faculty;
    }
}
