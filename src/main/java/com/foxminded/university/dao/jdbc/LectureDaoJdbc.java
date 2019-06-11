package com.foxminded.university.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class LectureDaoJdbc implements LectureDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private TeacherDaoJdbc teacherDao = new TeacherDaoJdbc();
    private GroupDaoJdbc groupDao = new GroupDaoJdbc();
    private CourseDaoJdbc courseDao = new CourseDaoJdbc();
    private ClassroomDaoJdbc classroomDao = new ClassroomDaoJdbc();
    
    @Override
    public Lecture create(Lecture lecture) {
        String sql = "INSERT INTO schedule (date,time,\"group\",teacher,course,classroom) VALUES(?,?,?,?,?,?)";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            statement.setObject(1, lecture.getDate());
            statement.setObject(2, lecture.getTime());
            statement.setInt(3, lecture.getGroup().getId());
            statement.setInt(4, lecture.getTeacher().getId());
            statement.setInt(5, lecture.getCourse().getId());
            statement.setInt(6, lecture.getClassroom().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            
            lecture.setId(resultSet.getInt("id"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lecture;
    }
    
    @Override
    public Lecture findById(int id) {
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE id=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return lecture;
            }
            
            lecture = getLectureFromResultSet(resultSet);
            lectures.add(lecture);
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lecture;
    }
    
    @Override
    public List<Lecture> findByTeacher(Teacher teacher) {
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE teacher=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, teacher.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = getLectureFromResultSet(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByGroup(Group group) {
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE \"group\"=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, group.getId());
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = getLectureFromResultSet(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByDate(LocalDate date) {
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM schedule WHERE date=?";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setObject(1, date);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = getLectureFromResultSet(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findAll() {
        
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        String sql = "SELECT * FROM schedule";
        
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = getLectureFromResultSet(resultSet);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public void update(Lecture lecture) {
        StringBuilder sb = new StringBuilder("SELECT * FROM schedule WHERE id=");
        sb.append(lecture.getId());
        String sql = sb.toString();
        
        try (Connection connection = connFactory.getConnection();
                Statement statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_UPDATABLE);) {
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            resultSet.next();
            
            resultSet.updateObject("date", java.sql.Date.valueOf(lecture.getDate()));
            resultSet.updateObject("time", java.sql.Time.valueOf(lecture.getTime()));
            resultSet.updateInt("group", lecture.getGroup().getId());
            resultSet.updateInt("teacher", lecture.getTeacher().getId());
            resultSet.updateInt("course", lecture.getCourse().getId());
            resultSet.updateInt("classroom", lecture.getClassroom().getId());
            resultSet.updateRow();
            
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Lecture lecture) {
        
        String sql = "DELETE FROM schedule WHERE id=?";
        try (Connection connection = connFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, lecture.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    
    private Lecture getLectureFromResultSet(ResultSet resultSet) throws SQLException {
        Lecture lecture = new Lecture();
        lecture.setId(resultSet.getInt("id"));
        lecture.setDate(resultSet.getObject("date", LocalDate.class));
        lecture.setTime(resultSet.getObject("time", LocalTime.class));
        Teacher teacher = teacherDao.findById(resultSet.getInt("teacher"));
        lecture.setTeacher(teacher);
        Group group = groupDao.findById(resultSet.getInt("group"));
        lecture.setGroup(group);
        Course course = courseDao.findById(resultSet.getInt("course"));
        lecture.setCourse(course);
        Classroom classroom = classroomDao.findById(resultSet.getInt("classroom"));
        lecture.setClassroom(classroom);
        
        return lecture;
    }
}
