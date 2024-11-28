package org.culinary.academy.bo.custom.impl;

import org.culinary.academy.bo.custom.StudentBO;
import org.culinary.academy.dao.DAOFactory;
import org.culinary.academy.dao.custom.StudentDAO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO=(StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public Student search(String id) {
        return studentDAO.search(id);
    }

    @Override
    public boolean save(StudentDTO studentDTO) {
        return studentDAO.save(new Student(studentDTO.getSId(),studentDTO.getName(),studentDTO.getGender(),studentDTO.getAddress(),studentDTO.getDob(),studentDTO.getContact(),studentDTO.getRegisterDate()));
    }

    @Override
    public boolean update(StudentDTO studentDTO) {
        return studentDAO.update(new Student(studentDTO.getSId(),studentDTO.getName(),studentDTO.getGender(),studentDTO.getAddress(),studentDTO.getDob(),studentDTO.getContact(),studentDTO.getRegisterDate()));
    }

    @Override
    public boolean delete(String id) {
        return studentDAO.delete(id);

    }

    @Override
    public List<StudentDTO> getAll() {
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : studentDAO.getAll()) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentID(),
                    student.getStudentName(),
                    student.getGender(),
                    student.getAddress(),
                    student.getDob(),
                    student.getContact(),
                    student.getRegister_date())
            );
        }
        return studentDTOS;

    }

    @Override
    public String getNextId() {
        String id = studentDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("ST", "")) + 1;
        return String.format("ST%03d", newId);
    }
}
