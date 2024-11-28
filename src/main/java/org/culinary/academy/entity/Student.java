package org.culinary.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id",length = 50)
    private String studentID;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_gender")
    private String gender;


    @Column(name = "student_address")
    private String address;

    @Column(name = "student_dob")
    private Date dob;

    @Column(name = "student_contact")
    private String contact;

    @Column(name = "registration_date")
    private Date Register_date;
//
//    @OneToMany(cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            mappedBy = "student")
//    private List<Program> programs
//            = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,
                         fetch = FetchType.LAZY,
                         mappedBy = "student" )
    private List<Registration> registrationList=new ArrayList<>();


    public Student(String sId, String name, String gender, String address, Date dob, String contact, Date registerDate) {
    }
}
