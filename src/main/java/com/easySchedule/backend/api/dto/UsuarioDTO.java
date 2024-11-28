package com.easySchedule.backend.api.dto;

import com.easySchedule.backend.domain.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;
    private boolean ativo;
    private String escolaNome;
}
