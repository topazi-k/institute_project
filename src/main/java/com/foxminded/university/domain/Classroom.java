package com.foxminded.university.domain;

public class Classroom {
    private int id;
    private int number;
    private int capacity;
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public int getCapacity() {
        return capacity;
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
        Classroom other = (Classroom) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Classroom [number=" + number + ", capacity=" + capacity + "]";
    }
    
}
