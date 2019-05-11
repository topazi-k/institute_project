package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.dao.jdbc.StudentDaoJdbc;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentService {
    private StudentDao studentDao = new StudentDaoJdbc();
    
    public Student create(Student student) {
        return studentDao.create(student);
    }
    
    public Student findById(int id) {
        Student student = studentDao.findById(id);
        if (student == null) {
            throw new DataNotFoundException();
        }
        return student;
    }
    
    public List<Student> findAll() {
        return studentDao.findAll();
    }
    
    public List<Student> findByGroup(Group group) {
        return studentDao.findByGroup(group);
    }
    
    public List<Student> findStudentsWithoutGroup() {
        return studentDao.findStudentsWithoutGroup();
    }
    
    public void update(Student student) {
        studentDao.update(student);
    }
    
    public void delete(Student student) {
        studentDao.delete(student);
    }
}
