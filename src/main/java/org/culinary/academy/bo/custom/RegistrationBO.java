package org.culinary.academy.bo.custom;

import org.culinary.academy.bo.SuperBO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.RegistrationDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Registration;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    boolean save(RegistrationDTO registrationDTO);
    boolean update(RegistrationDTO registrationDTO);
    boolean delete(String id);
    List<RegistrationDTO> getAll();
    String getNextId();
    Registration search(String id);
    List<String> getStudentId();
    List<String> getProgramId();
    StudentDTO getStudent(String value);
    ProgramDTO getProgram(String value);

}
