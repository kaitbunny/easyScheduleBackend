package com.easySchedule.backend.api.dto;

import com.easySchedule.backend.domain.model.enums.Periodo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TurmaDTO {
    private Long id;
    private boolean ativo;
    private Periodo periodo;
    private Integer semestre;
    private String cursoNome;
}
