package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.CursoDTO;
import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.Escola;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoDTO toDTO(Curso curso) {
        CursoDTO dto = new CursoDTO();
        dto.setId(curso.getId());
        dto.setNome(curso.getNome());
        dto.setPeriodos(curso.getPeriodos());
        dto.setEscolaId(curso.getEscola() != null ? curso.getEscola().getId() : null);
        dto.setEscolaNome(curso.getEscola() != null ? curso.getEscola().getNome() : null);
        return dto;
    }

    public Curso toEntity(CursoDTO inputDTO) {
        Curso curso = new Curso();
        curso.setId(inputDTO.getId());
        curso.setNome(inputDTO.getNome());
        curso.setPeriodos(inputDTO.getPeriodos());

        if (inputDTO.getEscolaId() != null) {
            Escola escola = new Escola();
            escola.setId(inputDTO.getEscolaId());
            escola.setNome(inputDTO.getEscolaNome());
            curso.setEscola(escola);
        }

        return curso;
    }
}
