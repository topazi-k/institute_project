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
    
    public void setCpacity(int capacity) {
        this.capacity = capacity;
    }
    
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
        result = prime * result + id;
        result = prime * result + number;
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
        if (capacity != other.capacity)
            return false;
        if (id != other.id)
            return false;
        if (number != other.number)
            return false;
        return true;
    }
    
}
