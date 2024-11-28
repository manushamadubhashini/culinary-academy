package org.culinary.academy.dao.custom;

import org.culinary.academy.dao.CrudDAO;
import org.culinary.academy.entity.User;

public interface UserDAO extends CrudDAO<User> {
    User searchByUsername(String username);
}

