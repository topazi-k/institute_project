package com.foxminded.university.dao.hibernate;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
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
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(lecture);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lecture;
    }
    
    @Override
    public Lecture findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Lecture lecture;
        try {
            lecture = em.find(Lecture.class, id);
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lecture;
    }
    
    @Override
    public List<Lecture> findAll() {
        EntityManager em = connFactory.getEntityManager();
        List<Lecture> lectures;
        try {
            TypedQuery<Lecture> query = em.createQuery("SELECT l FROM Lecture l", Lecture.class);
            lectures = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByTeacher(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        List<Lecture> lectures;
        try {
            TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE l.teacher=?1", Lecture.class);
            query.setParameter(1, teacher);
            lectures = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByGroup(Group group) {
        EntityManager em = connFactory.getEntityManager();
        List<Lecture> lectures;
        try {
            TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE l.group=?1", Lecture.class);
            query.setParameter(1, group);
            lectures = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public List<Lecture> findByDate(LocalDate date) {
        EntityManager em = connFactory.getEntityManager();
        List<Lecture> lectures;
        try {
            TypedQuery<Lecture> query = em.createQuery("Select l FROM Lecture l WHERE date=?1", Lecture.class);
            query.setParameter(1, date);
            lectures = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return lectures;
    }
    
    @Override
    public void update(Lecture lecture) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(lecture);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Lecture lecture) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(lecture);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
}
