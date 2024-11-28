package org.culinary.academy.bo.custom.impl;

import org.culinary.academy.bo.custom.ProgramBO;
import org.culinary.academy.dao.DAOFactory;
import org.culinary.academy.dao.custom.ProgramDAO;
import org.culinary.academy.dto.ProgramDTO;
import org.culinary.academy.dto.StudentDTO;
import org.culinary.academy.entity.Program;
import org.culinary.academy.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {

    ProgramDAO programDAO=(ProgramDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);
    @Override
    public boolean save(ProgramDTO programDTO) {
        return programDAO.save(new Program(programDTO.getPid(),programDTO.getName(),programDTO.getDuration(),programDTO.getFee()));
    }
    @Override
    public boolean update(ProgramDTO programDTO) {
        return programDAO.update(new Program(programDTO.getPid(),programDTO.getName(),programDTO.getDuration(),programDTO.getFee()));
    }

    @Override
    public boolean delete(String id) {
        return programDAO.delete(id);
    }

    @Override
    public List<ProgramDTO> getAll() {
        List<ProgramDTO> programDTOS = new ArrayList<>();
        for (Program program : programDAO.getAll()) {
            programDTOS.add(new ProgramDTO(
                    program.getProgramID(),
                    program.getProgramName(),
                    program.getDuration(),
                    program.getFee())
            );
        }
        return programDTOS;
    }

    @Override
    public String getNextId() {
        String id = programDAO.getNextId();
        Integer newId = Integer.parseInt(id.replace("PR", "")) + 1;
        return String.format("PR%03d", newId);
    }

    @Override
    public Program search(String id) {
       return programDAO.search(id);
    }
}
