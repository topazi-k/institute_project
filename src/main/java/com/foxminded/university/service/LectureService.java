package com.foxminded.university.service;

import java.time.LocalDate;
import java.util.List;

import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.dao.hibernate.LectureDaoHibernate;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class LectureService {
    private LectureDao lectureDao = new LectureDaoHibernate();
    
    public Lecture create(Lecture lecture) {
        return lectureDao.create(lecture);
    }
    
    public Lecture findById(int id) {
        Lecture lecture = lectureDao.findById(id);
        if (lecture == null) {
            throw new DataNotFoundException();
        }
        return lecture;
    }
    
    public List<Lecture> findByTeacher(Teacher teacher) {
        return lectureDao.findByTeacher(teacher);
    }
    
    public List<Lecture> findByGroup(Group group) {
        return lectureDao.findByGroup(group);
    }
    
    public List<Lecture> findByDate(LocalDate date) {
        return lectureDao.findByDate(date);
    }
    
    public List<Lecture> findAll() {
        return lectureDao.findAll();
    }
    
    public void update(Lecture lecture) {
        lectureDao.update(lecture);
    }
    
    public void delete(Lecture lecture) {
        lectureDao.delete(lecture);
    }
}
