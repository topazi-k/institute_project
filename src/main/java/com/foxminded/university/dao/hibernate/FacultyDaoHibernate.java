package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.DaoException;
import com.foxminded.university.dao.FacultyDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Teacher;

public class FacultyDaoHibernate implements FacultyDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Faculty create(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return faculty;
    }
    
    @Override
    public Faculty findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Faculty faculty;
        try {
            TypedQuery<Faculty> query = em
                    .createQuery("SELECT f FROM Faculty f LEFT JOIN FETCH f.groups g where f.id=?1", Faculty.class);
            query.setParameter(1, id);
            faculty = query.getSingleResult();
            faculty.getCourses().size();
            faculty.getTeachers().size();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return faculty;
    }
    
    @Override
    public List<Faculty> findAll() {
        EntityManager em = connFactory.getEntityManager();
        List<Faculty> faculties;
        try {
            TypedQuery<Faculty> query = em.createQuery("SELECT f FROM Faculty f", Faculty.class);
            faculties = query.getResultList();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
        return faculties;
    }
    
    @Override
    public void update(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addCourse(Course course, Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            faculty.addCourse(course);
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeCourse(Course course) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createNativeQuery("SELECT faculty FROM course WHERE id=?1");
            query.setParameter(1, course.getId());
            int facultyId = (int) query.getSingleResult();
            Faculty faculty = findById(facultyId);
            faculty.getCourses().remove(course);
            transaction.begin();
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addTeacher(Teacher teacher, Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            faculty.addTeacher(teacher);
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeTeacher(Teacher teacher) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createNativeQuery("SELECT faculty FROM teacher WHERE id=?1");
            query.setParameter(1, teacher.getId());
            int facultyId = (int) query.getSingleResult();
            Faculty faculty = findById(facultyId);
            faculty.getTeachers().remove(teacher);
            transaction.begin();
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void addGroup(Group group, Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            faculty.addGroup(group);
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void removeGroup(Group group) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            Query query = em.createNativeQuery("SELECT faculty FROM groups WHERE id=?1");
            query.setParameter(1, group.getId());
            int facultyId = (int) query.getSingleResult();
            Faculty faculty = findById(facultyId);
            faculty.getGroups().remove(group);
            transaction.begin();
            em.merge(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delete(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        try {
            EntityTransaction transaction = em.getTransaction();
            faculty.getCourses().clear();
            faculty.getTeachers().clear();
            faculty.getGroups().clear();
            update(faculty);
            transaction.begin();
            em.remove(faculty);
            transaction.commit();
            em.close();
        } catch (IllegalStateException | PersistenceException e) {
            throw new DaoException(e);
        }
    }
}
