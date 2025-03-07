package com.easySchedule.backend.api.dto;

import com.easySchedule.backend.domain.model.enums.TipoAdministrador;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdministradorDTO {
    private Long id;
    private String nome;
    private String email;
    private TipoAdministrador tipo;
    private String senha;
    private boolean ativo;
    private String escolaNome;
    private Long escolaId;
}