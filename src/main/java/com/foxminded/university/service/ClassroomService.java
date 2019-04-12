package com.foxminded.university.service;

import java.util.List;

import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.dao.jdbc.ClassroomDaoJdbc;
import com.foxminded.university.domain.Classroom;

public class ClassroomService {
    
    private ClassroomDao classroomDao = new ClassroomDaoJdbc();
    
    public Classroom create(Classroom classroom) {
        return classroomDao.create(classroom);
    }
    
    public Classroom findById(int id) {
        return classroomDao.findById(id);
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
