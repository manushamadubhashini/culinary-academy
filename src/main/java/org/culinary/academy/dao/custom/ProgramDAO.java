package org.culinary.academy.dao.custom;

import org.culinary.academy.dao.CrudDAO;
import org.culinary.academy.entity.Program;

public interface ProgramDAO extends CrudDAO<Program> {
    String getNextId();
}
