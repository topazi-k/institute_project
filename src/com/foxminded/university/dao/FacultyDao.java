package com.foxminded.university.dao;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

public interface FacultyDao {
    
    public Faculty createFaculty(Faculty faculty) throws DaoException;
    
    public Faculty findById(int id) throws DaoException;
    
    public Faculty updateInformation(Faculty faculty) throws DaoException;
    
    public void addCourse(Course course, Faculty faculty) throws DaoException;
    
    public void removeCourse(Course course) throws DaoException;
    
    public void addTeacher(Teacher teacher, Faculty faculty) throws DaoException;
    
    public void removeTeacher(Teacher teacher) throws DaoException;
    
    public void addGroup(Group group, Faculty faculty) throws DaoException;
    
    public void removeGroup(Group group) throws DaoException;
}
