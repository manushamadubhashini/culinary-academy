package org.culinary.academy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String sId;
    private String name;
    private String gender;
    private String address;
    private Date dob;
    private String contact;
    private Date registerDate;
}
