package com.foxminded.university.service;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super();
    }
    
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
    
    public DataNotFoundException(String message) {
        super(message);
    }
}
