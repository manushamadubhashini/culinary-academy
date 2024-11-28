package org.culinary.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    private String rid;
    private Date date;
    private String sid;
    private String pid;
    private String sName;
    private String pName;

}
