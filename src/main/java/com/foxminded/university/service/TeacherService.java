package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.dao.jdbc.TeacherDaoJdbc;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Teacher;

public class TeacherService {
    private TeacherDao teacherDao = new TeacherDaoJdbc();
    
    public Teacher create(Teacher teacher) {
        return teacherDao.create(teacher);
    }
    
    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }
    
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }
    
    public List<Teacher> findByFaculty(Faculty faculty) {
        return teacherDao.findByFaculty(faculty);
    }
    
    public void update(Teacher teacher) {
        teacherDao.update(teacher);
    }
    
    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }
    
}
