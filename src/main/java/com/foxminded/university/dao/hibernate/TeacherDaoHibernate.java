package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class TeacherDaoHibernate implements TeacherDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private LectureDao lectureDao = new LectureDaoHibernate();
    
    @Override
    public Teacher create(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(teacher);
        transaction.commit();
        em.close();
        return teacher;
    }
    
    @Override
    public Teacher findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Teacher teacher = em.find(Teacher.class, id);
        em.close();
        return teacher;
    }
    
    @Override
    public List<Teacher> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
        List<Teacher> teachers = query.getResultList();
        em.close();
        return teachers;
    }
    
    @Override
    public List<Teacher> findByFaculty(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM teacher WHERE faculty=?1", Teacher.class);
        query.setParameter(1, faculty);
        @SuppressWarnings("unchecked")
        List<Teacher> teachers = query.getResultList();
        em.close();
        return teachers;
    }
    
    @Override
    public void update(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(teacher);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        removeTeacherFromLectures(teacher);
        transaction.begin();
        em.remove(teacher);
        transaction.commit();
        em.close();
    }
    
    private void removeTeacherFromLectures(Teacher teacher) {
        List<Lecture> lectures = lectureDao.findByTeacher(teacher);
        for (Lecture lecture : lectures) {
            lecture.setTeacher(null);
            lectureDao.update(lecture);
        }
    }
}
