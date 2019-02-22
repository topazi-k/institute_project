package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;

public interface CourseDao {
    public Course create(Course course) throws DaoException;
    public Course findById(int id) throws DaoException;
    public List<Course> findAll() throws DaoException;
    public List<Course> findByFaculty(Faculty faculty) throws DaoException;
    public void updateInformation(Course course) throws DaoException;
    public void removeCourse(Course course) throws DaoException;
}
