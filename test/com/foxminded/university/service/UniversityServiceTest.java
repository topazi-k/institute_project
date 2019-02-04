package com.foxminded.university.service;

import com.foxminded.university.domain.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UniversityServiceTest {
    UniversityService university = new UniversityService();
    
    Lecture lecture1 = new Lecture();
    Lecture lecture2 = new Lecture();
    Lecture lecture3 = new Lecture();
    Lecture lecture4 = new Lecture();
    Lecture lecture5 = new Lecture();
    
    Student student1 = new Student();
    Student student2 = new Student();
    
    Teacher teacher1 = new Teacher();
    Teacher teacher2 = new Teacher();
    
    Faculty faculty1 = new Faculty();
    Faculty faculty2 = new Faculty();
    
    Student studentNull;
    TimePeriodService timePeriodNull;
    Teacher teacherNull;
    
    @Before
    public void creatingData() {
        
        student1.setId(1);
        student2.setId(2);
        
        Group group1 = new Group();
        Group group2 = new Group();
        group1.setId(1);
        group1.addStudent(student1);
        
        group2.setId(2);
        group2.addStudent(student2);
        
        faculty1.setId(1);
        faculty1.addGroup(group1);
        faculty2.setId(2);
        faculty2.addGroup(group2);
        
        teacher1.setId(1);
        teacher2.setId(2);
        
        lecture1.setGroup(group1);
        lecture1.setTeacher(teacher1);
        lecture1.setDate(LocalDate.of(2000, 10, 10));
        
        lecture2.setGroup(group1);
        lecture2.setTeacher(teacher1);
        lecture2.setDate(LocalDate.of(2000, 10, 11));
        
        lecture3.setGroup(group1);
        lecture3.setTeacher(teacher1);
        lecture3.setDate(LocalDate.of(2000, 11, 10));
        
        lecture4.setGroup(group2);
        lecture4.setTeacher(teacher2);
        lecture4.setDate(LocalDate.of(2000, 10, 10));
        
        lecture5.setGroup(group2);
        lecture5.setTeacher(teacher2);
        lecture5.setDate(LocalDate.of(2000, 10, 11));
        
        university.addFaculty(faculty1);
        university.addFaculty(faculty2);
        university.addLecture(lecture1);
        university.addLecture(lecture2);
        university.addLecture(lecture3);
        university.addLecture(lecture4);
        university.addLecture(lecture5);
    }
    
    @Test
    public void shouldReturnAmountOfLectures() {
        assertEquals(5, (university.getSchedule()).size());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shoulThrowExceptionWhenStudentIsNull() {
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 12), LocalDate.of(2000, 10, 20));
        university.getStudentSchedule(studentNull, timePeriod);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimePeriodIsNull() {
        university.getStudentSchedule(student1, timePeriodNull);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEndOfPeriodLessThanStartOfPeriod() {
        TimePeriodService incorrectTimePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10), LocalDate.of(1999, 10, 10));
        university.getStudentSchedule(student1, incorrectTimePeriod);
    }
    
    @Test
    public void shouldReturnEmptyArrayInsteadStudentScheduleWhenTimePeriodDoesNotMatchWithLectures() {
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 12), LocalDate.of(2000, 10, 20));
        assertTrue((university.getStudentSchedule(student1, timePeriod)).isEmpty());
    }
    
    @Test
    public void shouldReturnStudentScheduleOnOneDayWhenTimePeriodIsOneDay() {
        List<Lecture> expected = new ArrayList<>();
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10));
        List<Lecture> schedule = (ArrayList<Lecture>) university.getStudentSchedule(student1, timePeriod);
        
        expected.add(lecture1);
        
        assertEquals(1, schedule.size());
        assertTrue(expected.containsAll(schedule));
    }
    
    @Test
    public void shouldReturnStudentScheduleOnOneMonthWhenTimePeriodIsOneMonth() {
        List<Lecture> expected = new ArrayList<>();
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10), LocalDate.of(2000, 11, 10));
        List<Lecture> schedule = (ArrayList<Lecture>) university.getStudentSchedule(student1, timePeriod);
        
        expected.add(lecture1);
        expected.add(lecture2);
        expected.add(lecture3);
        
        assertEquals(3, schedule.size());
        assertTrue(expected.containsAll(schedule));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void sholdThrowExceptionWhenTeacherIsNull() {
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 12), LocalDate.of(2000, 10, 20));
        university.getTeacherSchedule(teacherNull, timePeriod);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTimePeriodIsNullForTeacher() {
        university.getTeacherSchedule(teacher1, timePeriodNull);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenEndOfPeriodLessThanStartOfPeriodForTeacher() {
        TimePeriodService incorrectTimePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10), LocalDate.of(1999, 10, 10));
        university.getTeacherSchedule(teacher1, incorrectTimePeriod);
    }
    
    @Test
    public void shouldReturnEmptyArrayInsteadTeacherScheduleWhenTimePeriodDoesNotMatchWithLectures() {
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 12), LocalDate.of(2000, 10, 20));
        assertTrue((university.getTeacherSchedule(teacher1, timePeriod)).isEmpty());
    }
    
    @Test
    public void shouldReturnTeacherScheduleOnOneDayWhenTimePeriodIsOneDay() {
        List<Lecture> expected = new ArrayList<>();
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10));
        List<Lecture> schedule = (ArrayList<Lecture>) university.getTeacherSchedule(teacher1, timePeriod);
        
        expected.add(lecture1);
        
        assertEquals(1, schedule.size());
        assertTrue(expected.containsAll(schedule));
    }
    
    @Test
    public void shouldReturnTeacherScheduleOnOneMonthWhenTimePeriodIsOneMonth() {
        List<Lecture> expected = new ArrayList<>();
        TimePeriodService timePeriod = new TimePeriodService(LocalDate.of(2000, 10, 10), LocalDate.of(2000, 11, 10));
        List<Lecture> schedule = (ArrayList<Lecture>) university.getTeacherSchedule(teacher1, timePeriod);
        
        expected.add(lecture1);
        expected.add(lecture2);
        expected.add(lecture3);
        
        assertEquals(3, schedule.size());
        assertTrue(expected.containsAll(schedule));
    }
    
}
