package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.GroupDao;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Student;

public class GroupDaoHibernate implements GroupDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private LectureDao lectureDao = new LectureDaoHibernate();
    
    @Override
    public Group create(Group group) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(group);
        transaction.commit();
        em.close();
        return group;
    }
    
    @Override
    public Group findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g LEFT JOIN FETCH g.students WHERE g.id=?1",Group.class);
        query.setParameter(1, id);
        Group group = query.getSingleResult();
        em.close();
        return group;
    }
    
    @Override
    public List<Group> findByFaculty(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM groups WHERE faculty=?1", Group.class);
        query.setParameter(1, faculty.getId());
        @SuppressWarnings("unchecked")
        List<Group> groups = query.getResultList();
        em.close();
        return groups;
    }
    
    @Override
    public List<Group> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Group> query = em.createQuery("SELECT g FROM Group g", Group.class);
        List<Group> groups = query.getResultList();
        em.close();
        return groups;
    }
    
    @Override
    public void addStudent(Group group, Student student) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        group.addStudent(student);
        em.merge(group);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void removeStudent(Student student) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Query query = em.createNativeQuery("SELECT group_id FROM student WHERE id=?1");
        query.setParameter(1, student.getId());
        int id = (int) query.getSingleResult();
        Group group = findById(id);
        group.getStudents().remove(student);
        transaction.begin();
        em.merge(group);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void removeAllStudents(Group group) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        group.getStudents().clear();
        transaction.begin();
        em.merge(group);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void update(Group group) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(group);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Group group) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        removeAllStudents(group);
        removeGroupFromLectures(group);
        transaction.begin();
        em.remove(em.merge(group));
        transaction.commit();
        em.close();
    }
    
    private void removeGroupFromLectures(Group group) {
        List<Lecture> lectures = lectureDao.findByGroup(group);
        for (Lecture lecture : lectures) {
            lecture.setGroup(null);
            lectureDao.update(lecture);
        }
    }
}
