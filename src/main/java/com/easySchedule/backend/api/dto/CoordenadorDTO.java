package com.easySchedule.backend.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoordenadorDTO {

    private String nome;
    private String email;
    private String senha;
    private boolean ativo;
    private Long cursoId;
}
