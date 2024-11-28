package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.TurmaDTO;
import com.easySchedule.backend.domain.model.Curso;
import com.easySchedule.backend.domain.model.Turma;
import org.springframework.stereotype.Component;

@Component
public class TurmaMapper {
    public TurmaDTO toDTO(Turma turma) {
        TurmaDTO dto = new TurmaDTO();
        dto.setId(turma.getId());
        dto.setAtivo(turma.isAtivo());
        dto.setPeriodo(turma.getPeriodo());
        dto.setSemestre(turma.getSemestre());
        dto.setCursoNome(turma.getCurso() != null ? turma.getCurso().getNome() : null);
        return dto;
    }

    public Turma toEntity(TurmaDTO dto) {
        Turma turma = new Turma();
        turma.setId(dto.getId());
        turma.setAtivo(dto.isAtivo());
        turma.setPeriodo(dto.getPeriodo());
        turma.setSemestre(dto.getSemestre());

        if (dto.getCursoNome() != null) {
            Curso curso = new Curso();
            curso.setNome(dto.getCursoNome());
            turma.setCurso(curso);
        }

        return turma;
    }
}
