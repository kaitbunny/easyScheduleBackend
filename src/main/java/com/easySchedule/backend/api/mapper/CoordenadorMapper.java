package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.CoordenadorDTO;
import com.easySchedule.backend.domain.model.Coordenador;
import com.easySchedule.backend.domain.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorMapper {

    public CoordenadorDTO toDTO(Coordenador coordenador) {
        CoordenadorDTO dto = new CoordenadorDTO();
        dto.setId(coordenador.getId());
        dto.setNome(coordenador.getNome());
        dto.setEmail(coordenador.getEmail());
        dto.setAtivo(coordenador.isAtivo());
        dto.setCursoNome(coordenador.getCurso() != null ? coordenador.getCurso().getNome() : null);
        return dto;
    }

    public Coordenador toEntity(CoordenadorDTO inputDTO) {
        Coordenador coordenador = new Coordenador();
        coordenador.setId(inputDTO.getId());
        coordenador.setNome(inputDTO.getNome());
        coordenador.setEmail(inputDTO.getEmail());
        coordenador.setSenha(inputDTO.getSenha());
        coordenador.setAtivo(inputDTO.isAtivo());

        if (inputDTO.getCursoNome() != null) {
            Curso curso = new Curso();
            curso.setNome(inputDTO.getCursoNome());
            coordenador.setCurso(curso);
        }

        return coordenador;
    }
}
