package org.culinary.academy.dao.custom;

import org.culinary.academy.dao.SuperDAO;
import org.culinary.academy.dto.RegistrationDTO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<RegistrationDTO> getAll();
}
