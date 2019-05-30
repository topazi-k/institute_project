package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.jdbc.CourseDaoJdbc;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;

public class CourseService {
    private CourseDao courseDao = new CourseDaoJdbc();
    
    public Course create(Course course) {
        return courseDao.create(course);
    }
    
    public Course findById(int id) {
        Course course = courseDao.findById(id);
        if (course == null) {
            throw new DataNotFoundException();
        }
        return course;
    }
    
    public List<Course> findAll() {
        return courseDao.findAll();
    }
    
    public List<Course> findByFaculty(Faculty faculty) {
        return courseDao.findByFaculty(faculty);
    }
    
    public void update(Course course) {
        courseDao.update(course);
    }
    
    public void delete(Course corse) {
        courseDao.delete(corse);
    }
}
