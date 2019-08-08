package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class CourseDaoHibernate implements CourseDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Course create(Course course) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(course);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return course;
    }
    
    @Override
    public Course findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Course course;
        try {
            course = em.find(Course.class, id);
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return course;
    }
    
    @Override
    public List<Course> findAll() {
        EntityManager em = connFactory.getEntityManager();
        List<Course> courses;
        try {
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
            courses = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return courses;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Course> findByFaculty(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        List<Course> courses;
        try {
            Query query = em.createNativeQuery("SELECT * FROM course WHERE faculty=?1", Course.class);
            query.setParameter(1, faculty.getId());
            
            courses = (List<Course>) query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return courses;
    }
    
    @Override
    public void update(Course course) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(course);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Course course) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(course);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
}
