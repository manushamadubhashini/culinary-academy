package org.culinary.academy.dao.custom;

import org.culinary.academy.dao.CrudDAO;
import org.culinary.academy.entity.Student;

public interface StudentDAO extends CrudDAO<Student> {
    String getNextId();
}
