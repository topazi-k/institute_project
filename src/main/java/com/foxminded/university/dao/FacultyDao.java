package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

public interface FacultyDao extends CrudDao<Faculty> {
    
    void addCourse(Course course, Faculty faculty);
    
    void removeCourse(Course course);
    
    void addTeacher(Teacher teacher, Faculty faculty);
    
    void removeTeacher(Teacher teacher);
    
    void addGroup(Group group, Faculty faculty);
    
    void removeGroup(Group group);
    
}
