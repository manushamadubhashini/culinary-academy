package org.culinary.academy.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProgramTM {
    private String pid;
    private String name;
    private String duration;
    private String fee;
}
