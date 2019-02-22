package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Student;

public interface StudentDao {
    public Student createStudent(Student student) throws DaoException;
    
    public Student findById(int id) throws DaoException;
    
    public List<Student> findAll() throws DaoException;
    
    public List<Student> findByGroup(int groupId) throws DaoException;
    
    public Student updateInformation(Student student) throws DaoException;
    
    public void removeStudent(Student student) throws DaoException;
}
