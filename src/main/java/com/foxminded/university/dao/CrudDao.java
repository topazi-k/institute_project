package com.foxminded.university.dao;

import java.util.List;

public interface CrudDao<T> {
    
    T create(T t);
    
    T findById(int id);
    
    List<T> findAll();
    
    void update(T t);
    
    void delete(T t);
}
