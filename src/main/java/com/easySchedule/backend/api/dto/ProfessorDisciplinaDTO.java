package com.easySchedule.backend.api.dto;

import com.easySchedule.backend.domain.model.enums.Periodo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorDisciplinaDTO {
    private Long id;
    private Periodo periodo;
    private String professorNome;
    private String disciplinaNome;
}
