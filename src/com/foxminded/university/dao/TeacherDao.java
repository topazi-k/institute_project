package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Teacher;

public interface TeacherDao extends CrudDao<Teacher> {
    
    List<Teacher> findByFaculty(Faculty faculty);
    
}
