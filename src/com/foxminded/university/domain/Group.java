package com.foxminded.university.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private int id;
    private int groupNumber;
    private String groupName;
    private List<Student> students = new ArrayList<>();
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    public int getGroupNumber() {
        return groupNumber;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    public String getGroupName() {
        return groupName;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    public void addStudents(List<Student> students) {
        this.students.addAll(students);
    }
    
    public void removeStudent(Student student) {
        students.remove(student);
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
        Group other = (Group) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
