package com.foxminded.university.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.foxminded.university.dao.ConnectionFactory;
import com.foxminded.university.dao.CourseDao;
import com.foxminded.university.dao.LectureDao;
import com.foxminded.university.dao.TeacherDao;
import com.foxminded.university.domain.Course;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Teacher;

public class CourseDaoHibernate implements CourseDao {
    
    private ConnectionFactory connFactory = new ConnectionFactory();
    private LectureDao lectureDao = new LectureDaoHibernate();
    private TeacherDao teacherDao = new TeacherDaoHibernate();
    
    @Override
    public Course create(Course course) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(course);
        transaction.commit();
        em.close();
        return course;
    }
    
    @Override
    public Course findById(int id) {
        EntityManager em = connFactory.getEntityManager();
        Course course = em.find(Course.class, id);
        em.close();
        return course;
    }
    
    @Override
    public List<Course> findAll() {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c", Course.class);
        List<Course> courses = query.getResultList();
        em.close();
        return courses;
    }
    
    @Override
    public List<Course> findByFaculty(Faculty faculty) {
        EntityManager em = connFactory.getEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM course WHERE faculty=?1", Course.class);
        query.setParameter(1, faculty.getId());
        @SuppressWarnings("unchecked")
        List<Course> courses = (List<Course>) query.getResultList();
        em.close();
        return courses;
    }
    
    @Override
    public void update(Course course) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(course);
        transaction.commit();
        em.close();
    }
    
    @Override
    public void delete(Course course) {
        EntityManager em = connFactory.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        removeCourseFromLectures(course);
        removeCourseFromTeachers(course);
        transaction.begin();
        em.remove(course);
        transaction.commit();
        em.close();
    }
    
    private void removeCourseFromLectures(Course course) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Lecture> query = em.createQuery("SELECT l FROM Lecture l WHERE l.course=?1", Lecture.class);
        query.setParameter(1, course);
        List<Lecture> lectures = query.getResultList();
        for (Lecture lecture : lectures) {
            lecture.setGroup(null);
            lectureDao.update(lecture);
        }
    }
    
    private void removeCourseFromTeachers(Course course) {
        EntityManager em = connFactory.getEntityManager();
        TypedQuery<Teacher> query = em.createQuery("SELECT t FROM Teacher t WHRERE t.course=?1)", Teacher.class);
        query.setParameter(1, course);
        List<Teacher> teachers = query.getResultList();
        for (Teacher teacher : teachers) {
            teacher.setCourse(null);
            teacherDao.update(teacher);
        }
    }
}
