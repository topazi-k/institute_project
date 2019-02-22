package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public interface GroupDao {
    
    public Group create(Group group) throws DaoException;
    
    public Group findById(int groupId) throws DaoException;
    
    public List<Group> findByFaculty(int facultyId) throws DaoException;
    
    public List<Group> findAll() throws DaoException;
    
    public Group updateInformation(Group group) throws DaoException;
    
    public void addStudent(Group group, Student student) throws DaoException;
    
    public void removeStudent(Student student) throws DaoException;
    
    public void removeAllStudents(Group group) throws DaoException;
}
