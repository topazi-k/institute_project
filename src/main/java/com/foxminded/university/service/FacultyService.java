package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.dao.jdbc.FacultyDaoJdbc;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

public class FacultyService {
    private FacultyDao facultyDao = new FacultyDaoJdbc();
    
    public Faculty create(Faculty faculty) {
        return facultyDao.create(faculty);
    }
    
    public Faculty findById(int id) {
        Faculty faculty = facultyDao.findById(id);
        if (faculty == null) {
            throw new DataNotFoundException();
        }
        return faculty;
    }
    
    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }
    
    public void update(Faculty faculty) {
        facultyDao.update(faculty);
    }
    
    public void addCourse(Course course, Faculty faculty) {
        facultyDao.addCourse(course, faculty);
    }
    
    public void removeCourse(Course course) {
        facultyDao.removeCourse(course);
    }
    
    public void addTeacher(Teacher teacher, Faculty faculty) {
        facultyDao.addTeacher(teacher, faculty);
    }
    
    public void removeTeacher(Teacher teacher) {
        facultyDao.removeTeacher(teacher);
    }
    
    public void addGroup(Group group, Faculty faculty) {
        facultyDao.addGroup(group, faculty);
    }
    
    public void delete(Faculty faculty) {
        facultyDao.delete(faculty);
    }
    
}
