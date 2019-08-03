package com.foxminded.university.domain;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "schedule")
public class Lecture {
    @Id
    @SequenceGenerator(name = "scheduleSequence", sequenceName = "schedule_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheduleSequence")
    int id;
    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @OneToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;
    @OneToOne
    @JoinColumn(name = "course")
    private Course course;
    @OneToOne
    @JoinColumn(name = "classroom")
    private Classroom classroom;
    
    @Column(name = "lecture_date")
    private LocalDate date;
    @Column(name = "lecture_time")
    private LocalTime time;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    public Teacher getTeacher() {
        return teacher;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }
    
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    
    public Classroom getClassroom() {
        return classroom;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public LocalTime getTime() {
        return time;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((group == null) ? 0 : group.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
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
        Lecture other = (Lecture) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (group == null) {
            if (other.group != null)
                return false;
        } else if (!group.equals(other.group))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Lecture [group=" + group + ", teacher=" + teacher + ", course=" + course + ", classroom=" + classroom
                + ", date=" + date + ", time=" + time + "]";
    }
    
}
