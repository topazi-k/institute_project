package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class University {
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
    
    public List<Lecture> getTaceherSchedule(Teacher teacher, TimePeriod timePeriod) {
        List<Lecture> teacherSchedule = new ArrayList<>();
        schedule.forEach(lecture -> {
            if ((lecture.getTeacher()).equals(teacher)) {
                teacherSchedule.add(lecture);
            }
        });
        
        LocalDate startOfPeriod = timePeriod.getStartOfPeriod();
        LocalDate endOfPeriod = timePeriod.getEndOfPeriod();
        
        teacherSchedule.forEach(lecture -> {
            LocalDate lectureDate = lecture.getDate();
            if (lectureDate.isBefore(startOfPeriod) || lectureDate.isAfter(endOfPeriod)) {
                teacherSchedule.remove(lecture);
            }
        });
        return teacherSchedule;
    }
    
    public List<Lecture> getStudentSchedule(Student student, TimePeriod timePeriod) {
        List<Lecture> studentSchedule = new ArrayList<>();
        Group studentGroup = findStudentGroup(student);
        schedule.forEach(lecture -> {
            if ((lecture.getGroup()).equals(studentGroup)) {
                studentSchedule.add(lecture);
            }
        });
        
        LocalDate startOfPeriod = timePeriod.getStartOfPeriod();
        LocalDate endOfPeriod = timePeriod.getEndOfPeriod();
        
        studentSchedule.forEach(lecture -> {
            LocalDate lectureDate = lecture.getDate();
            if (lectureDate.isBefore(startOfPeriod) || lectureDate.isAfter(endOfPeriod)) {
                studentSchedule.remove(lecture);
            }
        });
        
        return studentSchedule;
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
