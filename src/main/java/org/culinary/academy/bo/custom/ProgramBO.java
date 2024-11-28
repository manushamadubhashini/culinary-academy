package org.culinary.academy.bo.custom;

import org.culinary.academy.bo.SuperBO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Student;

import java.util.List;

public interface ProgramBO extends SuperBO {
    boolean save(ProgramDTO programDTO);
    boolean update(ProgramDTO programDTO);
    boolean delete(String id);
    List<ProgramDTO> getAll();
    String getNextId();
    Program search(String id);
}
