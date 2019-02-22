package com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public interface LectureDao {
    
    public Lecture create(Lecture lecture) throws DaoException;
    
    public List<Lecture> findByTeacher(Teacher teacher) throws DaoException;
    
    public List<Lecture> findByGroup(Group group) throws DaoException;
    
    public List<Lecture> findByDate(LocalDate date) throws DaoException;
    
    public List<Lecture> findAll() throws DaoException;
    
    public Lecture updateInformation(Lecture lecture) throws DaoException;
    
    public void remove(Lecture lecture) throws DaoException;
    
}
