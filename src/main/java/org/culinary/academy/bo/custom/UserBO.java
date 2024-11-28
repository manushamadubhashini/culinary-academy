package org.culinary.academy.bo.custom;

import org.culinary.academy.bo.SuperBO;
import org.culinary.academy.dto.UserDTO;

public interface UserBO extends SuperBO {
    boolean save(UserDTO userDTO);
    boolean update(UserDTO userDTO);
    UserDTO getUser(UserDTO userDTO);
}
