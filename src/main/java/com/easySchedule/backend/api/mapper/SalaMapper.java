package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.SalaDTO;
import com.easySchedule.backend.domain.model.Escola;
import com.easySchedule.backend.domain.model.Sala;
import org.springframework.stereotype.Component;

@Component
public class SalaMapper {
    public SalaDTO toDTO(Sala sala) {
        SalaDTO dto = new SalaDTO();
        dto.setId(sala.getId());
        dto.setNumero(sala.getNumero());
        dto.setAndar(sala.getAndar());
        dto.setPredio(sala.getPredio());
        dto.setEscolaNome(sala.getEscola() != null ? sala.getEscola().getNome() : null );
        return dto;
    }

    public Sala toEntity (SalaDTO inputDTO) {
        Sala sala = new Sala();
        sala.setId(inputDTO.getId());
        sala.setNumero(inputDTO.getNumero());
        sala.setAndar(inputDTO.getAndar());
        sala.setPredio(inputDTO.getPredio());

        if (inputDTO.getEscolaNome() != null) {
            Escola escola = new Escola();
            escola.setNome(inputDTO.getEscolaNome());
            sala.setEscola(escola);
        }

        return sala;
    }
}
