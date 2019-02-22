package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Classroom;

public interface ClassroomDao {
    
    public Classroom create(Classroom classroom) throws DaoException;
    
    public Classroom findById(int id) throws DaoException;
    
    public List<Classroom> findAll() throws DaoException;
    
    public Classroom updateInformation(Classroom classroom) throws DaoException;
    
    public void remove(Classroom classroom) throws DaoException;
    
}
