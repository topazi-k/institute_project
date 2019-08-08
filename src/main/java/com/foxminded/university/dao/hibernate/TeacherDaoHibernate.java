package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class TeacherDaoHibernate implements TeacherDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Teacher create(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(teacher);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return teacher;
    }
    
    @Override
    public Teacher findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Teacher teacher;
        try {
            teacher = em.find(Teacher.class, id);
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return teacher;
    }
    
    @Override
    public List<Teacher> findAll() {
        EntityManager em = connFactory.getEntityManager();
        List<Teacher> teachers;
        try {
            TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t", Teacher.class);
            teachers = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return teachers;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Teacher> findByFaculty(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        List<Teacher> teachers;
        try {
            Query query = em.createNativeQuery("SELECT * FROM teacher WHERE faculty=?1", Teacher.class);
            query.setParameter(1, faculty);
            teachers = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return teachers;
    }
    
    @Override
    public void update(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(teacher);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(teacher);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
}
