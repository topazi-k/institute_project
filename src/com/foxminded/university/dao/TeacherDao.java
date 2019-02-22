package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Teacher;

public interface TeacherDao {
    public Teacher create(Teacher teacher) throws DaoException;
    
    public Teacher findById(int id) throws DaoException;
    
    public List<Teacher> findAll() throws DaoException;
    
    public List<Teacher> findByFaculty(Faculty faculty) throws DaoException;
    
    public void updateInformation(Teacher teacher) throws DaoException;
    
    public void removeTeacher(Teacher teacher) throws DaoException;
    
}
