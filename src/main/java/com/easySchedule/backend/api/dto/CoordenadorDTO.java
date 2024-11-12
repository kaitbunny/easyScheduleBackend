package com.easySchedule.backend.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoordenadorDTO {

    private Long id;
    private String nome;
    private String email;
    private boolean ativo;
    private Long cursoId;
}
