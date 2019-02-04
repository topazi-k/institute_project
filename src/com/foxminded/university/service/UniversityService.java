package com.foxminded.university.service;

import java.util.ArrayList;
import java.util.List;

import com.foxminded.university.domain.Classroom;
import com.foxminded.university.domain.Faculty;
import com.foxminded.university.domain.Group;
import com.foxminded.university.domain.Lecture;
import com.foxminded.university.domain.Student;
import com.foxminded.university.domain.Teacher;

import java.time.LocalDate;

public class UniversityService {
    private List<Faculty> faculties = new ArrayList<>();
    private List<Classroom> classrooms = new ArrayList<>();
    private List<Lecture> schedule = new ArrayList<>();
    
    public List<Faculty> getFaculties() {
        return faculties;
    }
    
    public List<Classroom> getClassrooms() {
        return classrooms;
    }
    
    public List<Lecture> getSchedule() {
        return schedule;
    }
    
    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }
    
    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }
    
    public void addClassroom(Classroom classroom) {
        classrooms.add(classroom);
    }
    
    public void removeClassroom(Classroom classroom) {
        classrooms.remove(classroom);
    }
    
    public void addLecture(Lecture lecture) {
        schedule.add(lecture);
    }
    
    public void removeLecture(Lecture lecture) {
        schedule.remove(lecture);
    }
    
    public List<Lecture> getTeacherSchedule(Teacher teacher, TimePeriodService timePeriod) {
        if (teacher == null) {
            throw new IllegalArgumentException("teacher is null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("timePeriod is null");
        }
        if (timePeriod.getStartOfPeriod().isAfter(timePeriod.getEndOfPeriod())) {
            throw new IllegalArgumentException("incorrect timePeriod");
        }
        List<Lecture> fullTeacherSchedule = new ArrayList<>();
        List<Lecture> teacherScheduleInPeriod = new ArrayList<>();
        for (Lecture lecture : schedule) {
            if ((lecture.getTeacher()).equals(teacher)) {
                fullTeacherSchedule.add(lecture);
            }
        }
        
        LocalDate startOfPeriod = timePeriod.getStartOfPeriod();
        LocalDate endOfPeriod = timePeriod.getEndOfPeriod();
        
        for (Lecture lecture : fullTeacherSchedule) {
            LocalDate lectureDate = lecture.getDate();
            if (lectureDate.isBefore(startOfPeriod) || lectureDate.isAfter(endOfPeriod)) {
                continue;
                
            }
            teacherScheduleInPeriod.add(lecture);
        }
        return teacherScheduleInPeriod;
    }
    
    public List<Lecture> getStudentSchedule(Student student, TimePeriodService timePeriod) {
        if (student == null) {
            throw new IllegalArgumentException("studint is null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("timePeriod is null");
        }
        if (timePeriod.getStartOfPeriod().isAfter(timePeriod.getEndOfPeriod())) {
            throw new IllegalArgumentException("incorrect timePeriod");
        }
        List<Lecture> fullStudentSchedule = new ArrayList<>();
        List<Lecture> studentScheduleInPeriod = new ArrayList<>();
        Group studentGroup = findStudentGroup(student);
        for (Lecture lecture : schedule) {
            if ((lecture.getGroup()).equals(studentGroup)) {
                fullStudentSchedule.add(lecture);
            }
            
        }
        LocalDate startOfPeriod = timePeriod.getStartOfPeriod();
        LocalDate endOfPeriod = timePeriod.getEndOfPeriod();
        
        for (Lecture lecture : fullStudentSchedule) {
            LocalDate lectureDate = lecture.getDate();
            if (lectureDate.isBefore(startOfPeriod) || lectureDate.isAfter(endOfPeriod)) {
                continue;
            }
            studentScheduleInPeriod.add(lecture);
        }
        return studentScheduleInPeriod;
    }
    
    private Group findStudentGroup(Student student) {
        Group studentGroup = null;
        for (Faculty faculty : faculties) {
            for (Group group : faculty.getGroups()) {
                if ((group.getStudents()).contains(student)) {
                    studentGroup = group;
                }
                if (!(studentGroup == null)) {
                    break;
                }
            }
            if (!(studentGroup == null)) {
                break;
            }
        }
        return studentGroup;
    }
    
}
