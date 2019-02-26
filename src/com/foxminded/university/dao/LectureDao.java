package com.foxminded.university.dao;

import java.time.LocalDate;
import java.util.List;

import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public interface LectureDao extends CrudDao<Lecture> {
    
    List<Lecture> findByTeacher(Teacher teacher);
    
    List<Lecture> findByGroup(Group group);
    
    List<Lecture> findByDate(LocalDate date);
    
}
