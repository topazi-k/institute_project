package com.foxminded.university.dao;

import java.util.List;

import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public interface GroupDao extends CrudDao<Group> {
    
    List<Group> findByFaculty(Faculty faculty);
    
    void addStudent(Group group, Student student);
    
    void removeStudent(Student student);
    
    void removeAllStudents(Group group);
    
}
