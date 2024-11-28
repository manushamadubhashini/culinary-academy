package org.culinary.academy.dao.custom;

import org.culinary.academy.bo.SuperBO;
import org.culinary.academy.dao.CrudDAO;
import org.culinary.academy.dao.SuperDAO;
import org.culinary.academy.entity.Registration;

public interface RegistrationDAO extends CrudDAO<Registration> {
    String getNextId();
}
