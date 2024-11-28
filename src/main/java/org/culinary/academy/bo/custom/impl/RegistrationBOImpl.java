package org.culinary.academy.bo.custom.impl;

import org.culinary.academy.bo.custom.RegistrationBO;
import org.culinary.academy.dao.DAOFactory;
import org.culinary.academy.dao.custom.ProgramDAO;
import org.culinary.academy.dao.custom.QueryDAO;
import org.culinary.academy.dao.custom.RegistrationDAO;
import org.culinary.academy.dao.custom.StudentDAO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.RegistrationDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Registration;
import org.culinary.academy.entity.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {

    RegistrationDAO registrationDAO=(RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    StudentDAO studentDAO=(StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO=(ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);
    QueryDAO queryDAO=(QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean save(RegistrationDTO registrationDTO) {
        Student student=studentDAO.search(registrationDTO.getSid());
        Program program=programDAO.search(registrationDTO.getPid());
        return registrationDAO.save(new Registration(registrationDTO.getRid(), (Date) registrationDTO.getDate(),student,program));
    }

    @Override
    public boolean update(RegistrationDTO registrationDTO) {
        Student student=studentDAO.search(registrationDTO.getSid());
        Program program=programDAO.search(registrationDTO.getPid());
        return registrationDAO.update(new Registration(registrationDTO.getRid(), (Date) registrationDTO.getDate(),student,program));
    }

    @Override
    public boolean delete(String id) {
        return registrationDAO.delete(id);
    }

    @Override
    public List<RegistrationDTO> getAll() {
        List<RegistrationDTO> registrationDTOS = queryDAO.getAll();
        return registrationDTOS;
    }

    @Override
    public String getNextId() {
        String id = registrationDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("RE", "")) + 1;
        return String.format("RE%03d", newId);
    }

    @Override
    public Registration search(String id) {
        return null;
    }

    @Override
    public List<String> getStudentId() {
        List<String> studentId = new ArrayList<>();
        for (Student student : studentDAO.getAll()) {
            studentId.add(student.getStudentID());
        }
        return studentId;
    }

    @Override
    public List<String> getProgramId() {
        List<String> programId = new ArrayList<>();
        for (Program program : programDAO.getAll()) {
            programId.add(program.getProgramID());
        }
        return programId;
    }

    @Override
    public StudentDTO getStudent(String value) {
      Student student=studentDAO.search(value);
      return new StudentDTO(
              student.getStudentID(),
              student.getStudentName(),
              student.getGender(),
              student.getAddress(),
              student.getDob(),
              student.getContact(),
              student.getRegister_date()
      );
    }

    @Override
    public ProgramDTO getProgram(String value) {
       Program program=programDAO.search(value);
       return new ProgramDTO(
               program.getProgramID(),
               program.getProgramName(),
               program.getDuration(),
               program.getFee()
       );
    }
}
