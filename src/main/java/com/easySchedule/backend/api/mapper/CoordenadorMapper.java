package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.CoordenadorDTO;
import com.easySchedule.backend.domain.model.Coordenador;
import com.easySchedule.backend.domain.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CoordenadorMapper {

    public static CoordenadorDTO toDTO(Coordenador coordenador) {
        CoordenadorDTO dto = new CoordenadorDTO();
        dto.setNome(coordenador.getNome());
        dto.setEmail(coordenador.getEmail());
        dto.setAtivo(coordenador.isAtivo());
        dto.setCursoId(coordenador.getCurso() != null ? coordenador.getCurso().getId() : null);
        return dto;
    }

    public static Coordenador toEntity(CoordenadorDTO inputDTO) {
        Coordenador coordenador = new Coordenador();
        coordenador.setNome(inputDTO.getNome());
        coordenador.setEmail(inputDTO.getEmail());
        coordenador.setSenha(inputDTO.getSenha());
        coordenador.setAtivo(inputDTO.isAtivo());

        if (inputDTO.getCursoId() != null) {
            Curso curso = new Curso();
            curso.setId(inputDTO.getCursoId());
            coordenador.setCurso(curso);
        }

        return coordenador;
    }
}
