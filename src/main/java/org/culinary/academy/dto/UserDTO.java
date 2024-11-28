package org.culinary.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userName;
    private String password;
    private String userType;

    public UserDTO(String userName, String password) {
        this.userName=userName;
        this.password=password;
    }
}
