package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.jdbc.GroupDaoJdbc;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class GroupService {
    
    private GroupDao groupDao = new GroupDaoJdbc();
    
    public Group create(Group group) {
        return groupDao.create(group);
    }
    
    public Group findById(int id) {
        Group group = groupDao.findById(id);
        if (group == null) {
            throw new DataNotFoundException();
        }
        return group;
    }
    
    public List<Group> findByFaculty(Faculty faculty) {
        return groupDao.findByFaculty(faculty);
    }
    
    public List<Group> findAll() {
        return groupDao.findAll();
    }
    
    public void update(Group group) {
        groupDao.update(group);
    }
    
    public void addStudent(Group group, Student student) {
        groupDao.addStudent(group, student);
    }
    
    public void removeStudent(Student student) {
        groupDao.removeStudent(student);
    }
    
    public void removeAllStudents(Group group) {
        groupDao.removeAllStudents(group);
    }
    
    public void delete(Group group) {
        groupDao.delete(group);
    }
    
}
