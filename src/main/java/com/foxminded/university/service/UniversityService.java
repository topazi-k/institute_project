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
    private List<Faculty> faculties = new FacultyService().findAll();
    private List<Lecture> schedule = new LectureService().findAll();
    
    public List<Faculty> getFaculties() {
        return faculties;
    }
    
    public List<Lecture> getSchedule() {
        return schedule;
    }
    
    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
    
    public void setSchedule(List<Lecture> schedule) {
        this.schedule = schedule;
    }
    
    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }
    
    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
    }
    
    public void addLecture(Lecture lecture) {
        schedule.add(lecture);
    }
    
    public void removeLecture(Lecture lecture) {
        schedule.remove(lecture);
    }
    
    public List<Lecture> getTeacherSchedule(int teacherId, TimePeriodService timePeriod) {
        if (teacherId == 0) {
            throw new IllegalArgumentException("teacher id is null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("timePeriod is null");
        }
        if (timePeriod.getStartOfPeriod().isAfter(timePeriod.getEndOfPeriod())) {
            throw new IllegalArgumentException("incorrect timePeriod");
        }
        Teacher teacher = new TeacherService().findById(teacherId);
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
    
    public List<Lecture> getStudentSchedule(int studentId, TimePeriodService timePeriod) {
        
        if (studentId == 0) {
            throw new IllegalArgumentException("student id is null");
        }
        if (timePeriod == null) {
            throw new IllegalArgumentException("timePeriod is null");
        }
        if (timePeriod.getStartOfPeriod().isAfter(timePeriod.getEndOfPeriod())) {
            throw new IllegalArgumentException("incorrect timePeriod");
        }
        Student student = new StudentService().findById(studentId);
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
