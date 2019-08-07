package com.foxminded.university.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @SequenceGenerator(name = "teacherSequence", sequenceName = "teacher_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacherSequence")
    private int id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @OneToOne
    @JoinColumn(name = "course")
    private Course course;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
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
        Teacher other = (Teacher) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Teacher [firstName=" + firstName + ", lastName=" + lastName + ", course=" + course + "]";
    }
    
}
