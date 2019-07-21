package com.foxminded.university.dao.hibernate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class LectureDaoHibernate implements LectureDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Lecture create(Lecture lecture) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(lecture);
        transaction.commit();
        em.close();
        return lecture;
    }
    
    @Override
    public Lecture findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Lecture lecture = em.find(Lecture.class, id);
        em.close();
        return lecture;
    }
    
    @Override
    public List<Lecture> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("SELECT l FROM Lecture l", Lecture.class);
        List<Lecture> lectures = query.getResultList();
        em.close();
        return lectures;
    }
    
    @Override
    public List<Lecture> findByTeacher(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE l.teacher=?1", Lecture.class);
        query.setParameter(1, teacher);
        List<Lecture> lectures = query.getResultList();
        em.close();
        return lectures;
    }
    
    @Override
    public List<Lecture> findByGroup(Group group) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE l.group=?1", Lecture.class);
        query.setParameter(1, group);
        List<Lecture> lectures = query.getResultList();
        em.close();
        return lectures;
    }
    
    @Override
    public List<Lecture> findByDate(LocalDate date) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE date=?1", Lecture.class);
        query.setParameter(1, date);
        List<Lecture> lectures = query.getResultList();
        em.close();
        return lectures;
    }
    
    @Override
    public void update(Lecture lecture) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(lecture);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Lecture lecture) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(lecture);
        transaction.commit();
        em.close();
    }
}
