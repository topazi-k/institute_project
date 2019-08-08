package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @SequenceGenerator(name = "facultySequence", sequenceName = "faculty_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facultySequence")
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany
    @JoinColumn(name = "faculty")
    private List<Teacher> teachers = new ArrayList<>();
    
    @OneToMany
    @JoinColumn(name = "faculty")
    private List<Group> groups = new ArrayList<>();
    
    @OneToMany
    @JoinColumn(name = "faculty")
    private List<Course> courses = new ArrayList<>();
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Teacher> getTeachers() {
        return teachers;
    }
    
    public List<Group> getGroups() {
        return groups;
    }
    
    public List<Course> getCourses() {
        return courses;
    }
    
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
    
    public void addTeachers(List<Teacher> teachers) {
        this.teachers.addAll(teachers);
    }
    
    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }
    
    public void addGroup(Group group) {
        groups.add(group);
    }
    
    public void addGroups(List<Group> groups) {
        this.groups.addAll(groups);
    }
    
    public void removeGroup(Group group) {
        groups.remove(group);
    }
    
    public void addCourse(Course course) {
        courses.add(course);
    }
    
    public void addCourses(List<Course> courses) {
        this.courses.addAll(courses);
    }
    
    public void removeCourse(Course course) {
        courses.remove(course);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Faculty other = (Faculty) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Faculty [name=" + name + "]";
    }
    
}
