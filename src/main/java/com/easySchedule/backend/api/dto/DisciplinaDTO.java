package com.easySchedule.backend.api.dto;

import lombok.Data;

@Data
public class DisciplinaDTO {
    private Long id;
    private String nome;
    private boolean ativo;
    private Long cursoId;
    private String cursoNome;
}
