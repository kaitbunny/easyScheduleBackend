package com.easySchedule.backend.api.mapper;

import org.springframework.stereotype.Component;

import com.easySchedule.backend.api.dto.AdministradorDTO;
import com.easySchedule.backend.domain.model.Administrador;
import com.easySchedule.backend.domain.model.Escola;

@Component
public class AdministradorMapper {

    public AdministradorDTO toDTO(Administrador administrador) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setId(administrador.getId());
        dto.setNome(administrador.getNome());
        dto.setEmail(administrador.getEmail());
        dto.setTipo(administrador.getTipo());
        dto.setSenha(administrador.getSenha());
        dto.setAtivo(administrador.isAtivo());
        if (administrador.getEscola() != null) {
            dto.setEscolaNome(administrador.getEscola().getNome());
            dto.setEscolaId(administrador.getEscola().getId());
        }
        return dto;
    }

    public Administrador toEntity(AdministradorDTO inputDTO) {
        Administrador administrador = new Administrador();
        administrador.setId(inputDTO.getId());
        administrador.setNome(inputDTO.getNome());
        administrador.setEmail(inputDTO.getEmail());
        administrador.setTipo(inputDTO.getTipo());
        administrador.setSenha(inputDTO.getSenha());
        administrador.setAtivo(inputDTO.isAtivo());


        if (inputDTO.getEscolaId() != null) {
            Escola escola = new Escola();
            escola.setId(inputDTO.getEscolaId());
            escola.setNome(inputDTO.getEscolaNome());
            administrador.setEscola(escola);
        }

        return administrador;
    }
}
