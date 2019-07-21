package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ClassroomDao;
import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Lecture;

public class ClassroomDaoHibernate implements ClassroomDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private LectureDao lectureDao = new LectureDaoHibernate();
    
    @Override
    public Classroom create(Classroom classroom) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(classroom);
        transaction.commit();
        em.close();
        return classroom;
    }
    
    @Override
    public Classroom findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Classroom classroom = em.find(Classroom.class, id);
        em.close();
        return classroom;
    }
    
    @Override
    public List<Classroom> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Classroom> query = em.createQuery("SELECT c FROM Classroom c", Classroom.class);
        List<Classroom> classrooms = query.getResultList();
        em.close();
        return classrooms;
    }
    
    public void update(Classroom classroom) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(classroom);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Classroom classroom) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        removeClassroomFromLectuers(classroom);
        transaction.begin();
        em.remove(classroom);
        transaction.commit();
        em.close();
    }
    
    private void removeClassroomFromLectuers(Classroom classroom) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("SELECT l FROM Lecture l WHERE l.classroom=?1", Lecture.class);
        query.setParameter(1, classroom);
        List<Lecture> lectures = query.getResultList();
        for (Lecture lecture : lectures) {
            lecture.setClassroom(null);
            lectureDao.update(lecture);
        }
    }
}
