package com.easySchedule.backend.api.mapper;

import org.springframework.stereotype.Component;

import com.easySchedule.backend.api.dto.AdministradorDTO;
import com.easySchedule.backend.domain.model.Administrador;
import com.easySchedule.backend.domain.model.Escola;

@Component
public class AdministradorMapper {

    public AdministradorDTO toDTO(Administrador administrador) {
        AdministradorDTO dto = new AdministradorDTO();
        dto.setNome(administrador.getNome());
        dto.setEmail(administrador.getEmail());
        dto.setTipo(administrador.getTipo());
        dto.setAtivo(administrador.isAtivo());
        dto.setEscolaId(administrador.getEscola() != null ? administrador.getEscola().getId() : null);
        return dto;
    }

    public Administrador toEntity(AdministradorDTO inputDTO) {
        Administrador administrador = new Administrador();
        administrador.setNome(inputDTO.getNome());
        administrador.setEmail(inputDTO.getEmail());
        administrador.setTipo(inputDTO.getTipo());
        administrador.setSenha(inputDTO.getSenha());
        administrador.setAtivo(inputDTO.isAtivo());

        if (inputDTO.getEscolaId() != null) {
            Escola escola = new Escola();
            escola.setId(inputDTO.getEscolaId());
            administrador.setEscola(escola);
        }

        return administrador;
    }
}
