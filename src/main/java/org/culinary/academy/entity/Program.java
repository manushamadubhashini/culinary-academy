package org.culinary.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "program")
public class Program {
    @Id
    @Column(name = "program_id",length = 50)
    private String programID;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "duration")
    private String duration;

    @Column(name = "fee")
    private String  fee;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
                mappedBy = "program")
    private List<Registration> registrationList=new ArrayList<>();


    public Program(String pid, String name, String duration, String fee) {
        this.programID=pid;
        this.programName=name;
        this.duration=duration;
        this.fee=fee;
    }


}
