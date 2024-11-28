package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.ProfessorDisciplinaDTO;
import com.easySchedule.backend.domain.model.Disciplina;
import com.easySchedule.backend.domain.model.ProfessorDisciplina;
import com.easySchedule.backend.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ProfessorDisciplinaMapper {
    public ProfessorDisciplinaDTO toDTO (ProfessorDisciplina professorDisciplina){
        ProfessorDisciplinaDTO dto = new ProfessorDisciplinaDTO();
        dto.setId(professorDisciplina.getId());
        dto.setPeriodo(professorDisciplina.getPeriodo());
        dto.setDisciplinaNome(professorDisciplina.getProfessor() != null ? professorDisciplina.getProfessor().getNome() : null);
        dto.setProfessorNome(professorDisciplina.getDisciplina() != null ? professorDisciplina.getDisciplina().getNome() : null);

        return dto;
    }

    public ProfessorDisciplina toEntity (ProfessorDisciplinaDTO inputDTO) {
        ProfessorDisciplina professorDisciplina = new ProfessorDisciplina();
        professorDisciplina.setId(inputDTO.getId());
        professorDisciplina.setPeriodo(inputDTO.getPeriodo());

        if (inputDTO.getProfessorNome() != null ) {
            Usuario usuario = new Usuario();
            usuario.setNome(inputDTO.getDisciplinaNome());
            professorDisciplina.setProfessor(usuario);
        }

        if (inputDTO.getDisciplinaNome() != null ) {
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(inputDTO.getDisciplinaNome());
            professorDisciplina.setDisciplina(disciplina);
        }

        return professorDisciplina;
    }
}
