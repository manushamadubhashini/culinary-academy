package org.culinary.academy.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "registration")
public class Registration {
    @Id
    @Column(name = "reg_id",length = 50)
    private String reg_id;

    @Column(name = "reg_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;



}
