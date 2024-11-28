package org.culinary.academy.bo.custom;

import org.culinary.academy.bo.SuperBO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Student;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean save(StudentDTO studentDTO);
    boolean update(StudentDTO studentDTO);
    boolean delete(String id);
    List<StudentDTO> getAll();
    String getNextId();
    Student search(String id);


}
