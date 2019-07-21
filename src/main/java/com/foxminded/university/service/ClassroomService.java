package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.dao.hibernate.ClassroomDaoHibernate;
import com.foxminded.university.domain.Classroom;

public class ClassroomService {
    
    private ClassroomDao classroomDao = new ClassroomDaoHibernate();
    
    public Classroom create(Classroom classroom) {
        return classroomDao.create(classroom);
    }
    
    public Classroom findById(int id) {
        Classroom classroom = classroomDao.findById(id);
        if (classroom == null) {
            throw new DataNotFoundException();
        }
        return classroom;
    }
    
    public List<Classroom> findAll() {
        return classroomDao.findAll();
    }
    
    public void update(Classroom classroom) {
        classroomDao.update(classroom);
    }
    
    public void delete(Classroom classroom) {
        classroomDao.delete(classroom);
    }
}
