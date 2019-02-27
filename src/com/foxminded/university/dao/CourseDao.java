package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;

public interface CourseDao extends CrudDao<Course> {
    
    List<Course> findByFaculty(Faculty faculty);
    
}
