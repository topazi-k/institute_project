package com.foxminded.university.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class LectureDaoPostgreSql implements LectureDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Lecture create(Lecture lecture) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO schedule (date,time,\"group\",teacher,course,classroom) VALUES(?,?,?,?,?,?);";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, lecture.getDate());
            statement.setObject(2, lecture.getTime());
            statement.setInt(3, lecture.getGroup().getId());
            statement.setInt(4, lecture.getTeacher().getId());
            statement.setInt(5, lecture.getCourse().getId());
            statement.setInt(6, lecture.getClassroom().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            lecture.setId(resultSet.getInt("id"));
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
        return lecture;
    }
    
    @Override
    public List<Lecture> findByTeacher(Teacher teacher) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        GroupDaoPostgreSql groupDao = new GroupDaoPostgreSql();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        ClassroomDaoPostgreSql classroomDao = new ClassroomDaoPostgreSql();
        String sql = "SELECT * FROM schedule WHERE teacher=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, teacher.getId());
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = new Lecture();
                lecture.setId(resultSet.getInt("id"));
                lecture.setDate(resultSet.getObject("date", LocalDate.class));
                lecture.setTime(resultSet.getObject("time", LocalTime.class));
                lecture.setTeacher(teacher);
                Group group = groupDao.findById(resultSet.getInt("id"));
                lecture.setGroup(group);
                Course course = courseDao.findById(resultSet.getInt("course"));
                lecture.setCourse(course);
                Classroom classroom = classroomDao.findById(resultSet.getInt("classroom"));
                lecture.setClassroom(classroom);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByGroup(Group group) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        ClassroomDaoPostgreSql classroomDao = new ClassroomDaoPostgreSql();
        TeacherDaoPostgreSql teacherDao = new TeacherDaoPostgreSql();
        String sql = "SELECT * FROM schedule WHERE \"group\"=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, group.getId());
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = new Lecture();
                lecture.setId(resultSet.getInt("id"));
                lecture.setDate(resultSet.getObject("date", LocalDate.class));
                lecture.setTime(resultSet.getObject("time", LocalTime.class));
                Teacher teacher = teacherDao.findById(resultSet.getInt("teacher"));
                lecture.setTeacher(teacher);
                lecture.setGroup(group);
                Course course = courseDao.findById(resultSet.getInt("course"));
                lecture.setCourse(course);
                Classroom classroom = classroomDao.findById(resultSet.getInt("classroom"));
                lecture.setClassroom(classroom);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByDate(LocalDate date) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        ClassroomDaoPostgreSql classroomDao = new ClassroomDaoPostgreSql();
        TeacherDaoPostgreSql teacherDao = new TeacherDaoPostgreSql();
        GroupDaoPostgreSql groupDao = new GroupDaoPostgreSql();
        String sql = "SELECT * FROM schedule WHERE date=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setObject(1, date);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = new Lecture();
                lecture.setId(resultSet.getInt("id"));
                lecture.setDate(date);
                lecture.setTime(resultSet.getObject("time", LocalTime.class));
                Teacher teacher = teacherDao.findById(resultSet.getInt("teacher"));
                lecture.setTeacher(teacher);
                Group group = groupDao.findById(resultSet.getInt("group"));
                lecture.setGroup(group);
                Course course = courseDao.findById(resultSet.getInt("course"));
                lecture.setCourse(course);
                Classroom classroom = classroomDao.findById(resultSet.getInt("classroom"));
                lecture.setClassroom(classroom);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findAll() throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        Lecture lecture = null;
        List<Lecture> lectures = new ArrayList<>();
        CourseDaoPostgreSql courseDao = new CourseDaoPostgreSql();
        ClassroomDaoPostgreSql classroomDao = new ClassroomDaoPostgreSql();
        TeacherDaoPostgreSql teacherDao = new TeacherDaoPostgreSql();
        GroupDaoPostgreSql groupDao = new GroupDaoPostgreSql();
        String sql = "SELECT * FROM schedule;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                lecture = new Lecture();
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
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            throw new DaoException();
        }
        return lectures;
    }
    
    @Override
    public Lecture updateInformation(Lecture lecture) throws DaoException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        StringBuilder sb = new StringBuilder("SELECT * FROM schedule WHERE id=");
        sb.append(lecture.getId());
        sb.append(";");
        String sql = sb.toString();
        try {
            connection = connFactory.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(sql);
            
            resultSet.next();
            resultSet.updateObject("date", lecture.getDate());
            resultSet.updateObject("time", lecture.getTime());
            resultSet.updateInt("group", lecture.getGroup().getId());
            resultSet.updateInt("teacher", lecture.getTeacher().getId());
            resultSet.updateInt("course", lecture.getCourse().getId());
            resultSet.updateInt("classroom", lecture.getClassroom().getId());
            resultSet.updateRow();
        } catch (SQLException e) {
            throw new DaoException();
        }
        return lecture;
    }
    
    @Override
    public void remove(Lecture lecture) throws DaoException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "DELETE FROM schedule WHERE id=?;";
        try {
            connection = connFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, lecture.getId());
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
