package com.foxminded.university.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lecture {
    private Group group;
    private Teacher teacher;
    private Course course;
    private Classroom classroom;
    private LocalDate date;
    private LocalTime time;
    
    public void setGroup(Group group) {
        this.group=group;
    }
    public Group getGroup() {
        return group;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher=teacher;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setCourse(Course course) {
        this.course=course;
    }
    public Course getCourse() {
        return course;
    }
    public void setClassroom(Classroom classroom) {
        this.classroom=classroom;
    }
    public Classroom getClassroom() {
        return classroom;
    }
    public void setDate(LocalDate date) {
        this.date=date;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setTime(LocalTime time) {
        this.time=time;
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
        result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
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
        if (teacher == null) {
            if (other.teacher != null)
                return false;
        } else if (!teacher.equals(other.teacher))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        return true;
    }
   
    
}
