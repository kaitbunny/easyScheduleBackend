package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.DisciplinaDTO;
import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.Disciplina;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {
    public DisciplinaDTO toDTO(Disciplina disciplina) {
        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setId(disciplina.getId());
        dto.setNome(disciplina.getNome());
        dto.setAtivo(disciplina.isAtivo());
        dto.setCursoNome(disciplina.getCurso() != null ? disciplina.getCurso().getNome() : null);
        return dto;
    }

    public Disciplina toEntity(DisciplinaDTO inputDTO) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(inputDTO.getId());
        disciplina.setNome(inputDTO.getNome());
        disciplina.setAtivo(inputDTO.isAtivo());

        if (inputDTO.getCursoNome() != null) {
            Curso curso = new Curso();
            curso.setNome(inputDTO.getCursoNome());
            disciplina.setCurso(curso);
        }

        return disciplina;
    }
}
