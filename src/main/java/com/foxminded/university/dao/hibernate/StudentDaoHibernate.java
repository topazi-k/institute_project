package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.StudentDao;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Student;

public class StudentDaoHibernate implements StudentDao {
    private ConnectionFactory connFactory = new ConnectionFactory();
    
    @Override
    public Student create(Student student) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        transaction.commit();
        em.close();
        return student;
    }
    
    @Override
    public Student findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }
    
    @Override
    public List<Student> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = query.getResultList();
        em.close();
        return students;
    }
    
    @Override
    public List<Student> findByGroup(Group group) {
        EntityManager em = connFactory.getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM student WHERE group_id = ?1", Student.class);
        query.setParameter(1, group.getId());
        @SuppressWarnings("unchecked")
        List<Student> students =  query.getResultList();
        em.close();
        return students;
    }
    
    @Override
    public List<Student> findStudentsWithoutGroup() {
        EntityManager em = connFactory.getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM student where (group_id is null)", Student.class);
        @SuppressWarnings("unchecked")
        List<Student> studetns = query.getResultList();
        em.close();
        return studetns;
    }
    
    @Override
    public void update(Student student) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(student);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Student student) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(student));
        transaction.commit();
        em.close();
    }
}
