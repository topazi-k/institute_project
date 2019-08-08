package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoHibernate implements StudentDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Student create(Student student) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(student);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return student;
    }
    
    @Override
    public Student findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Student student;
        try {
            student = em.find(Student.class, id);
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        EntityManager em = connFactory.getEntityManager();
        List<Student> students;
        try {
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return students;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> findByGroup(Group group) {
        EntityManager em = connFactory.getEntityManager();
        List<Student> students;
        try {
            Query query = em.createNativeQuery("SELECT * FROM student WHERE group_id = ?1", Student.class);
            query.setParameter(1, group.getId());
            students = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return students;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> findStudentsWithoutGroup() {
        EntityManager em = connFactory.getEntityManager();
        List<Student> students;
        try {
            Query query = em.createNativeQuery("SELECT * FROM student where (group_id is null)", Student.class);
            students = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return students;
    }
    
    @Override
    public void update(Student student) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(student);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Student student) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(em.merge(student));
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
}
