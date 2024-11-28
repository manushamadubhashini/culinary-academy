package org.culinary.academy.dto;

import com.mysql.cj.exceptions.StreamingNotifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramDTO {
    private String pid;
    private String name;
    private String duration;
    private String fee;
}
