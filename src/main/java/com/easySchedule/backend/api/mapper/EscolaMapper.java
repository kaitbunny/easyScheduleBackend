package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.EscolaDTO;
import com.easySchedule.backend.domain.model.Escola;
import org.springframework.stereotype.Component;

@Component
public class EscolaMapper {
    public EscolaDTO toDTO(Escola escola) {
        EscolaDTO dto = new EscolaDTO();
        dto.setId(escola.getId());
        dto.setNome(escola.getNome());
        dto.setLogo(escola.getLogo());
        return dto;
    }

    public Escola toEntity(EscolaDTO inputDTO) {
        Escola escola = new Escola();
        escola.setId(inputDTO.getId());
        escola.setNome(inputDTO.getNome());
        escola.setLogo(inputDTO.getLogo());

        return escola;
    }
}
