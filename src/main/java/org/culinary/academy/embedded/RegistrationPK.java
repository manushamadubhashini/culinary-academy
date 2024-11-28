package org.culinary.academy.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RegistrationPK implements Serializable {

    @Column(name = "student_id",length = 50)
    private String studentID;

    @Column(name = "program_id",length = 50)
    private String programID;


}
