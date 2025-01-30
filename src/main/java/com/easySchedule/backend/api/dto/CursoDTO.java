package com.easySchedule.backend.api.dto;

import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.model.enums.Periodo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CursoDTO {
    private Long id;
    private String nome;
    private List<Periodo> periodos;
    private Long escolaId;
    private String escolaNome;
}
