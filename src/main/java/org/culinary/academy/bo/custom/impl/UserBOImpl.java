package org.culinary.academy.bo.custom.impl;

import org.culinary.academy.bo.custom.UserBO;
import org.culinary.academy.dao.DAOFactory;
import org.culinary.academy.dao.custom.UserDAO;
import org.culinary.academy.dto.UserDTO;
import org.culinary.academy.entity.User;

public class UserBOImpl implements UserBO {

    UserDAO userDAO=(UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getUserType()));
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return false;
    }

    @Override
    public UserDTO getUser(UserDTO userDTO) {
        User user = userDAO.searchByUsername(userDTO.getUserName());
        if (user != null) {
            return new UserDTO(user.getUserName(), user.getPassword());
        } else {
            return null;
        }
    }
}
