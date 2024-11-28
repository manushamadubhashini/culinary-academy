package org.culinary.academy.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    boolean save(T entity);
    boolean  update(T entity);
    boolean delete(String id);
    List<T> getAll();
    T search(String id);
}
