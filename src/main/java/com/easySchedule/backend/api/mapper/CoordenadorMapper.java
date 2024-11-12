package com.easySchedule.backend.api.mapper;

import com.easySchedule.backend.api.dto.CoordenadorDTO;
import com.easySchedule.backend.domain.model.Coordenador;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper {

    CoordenadorDTO toDTO(Coordenador coordenador);

    Coordenador toEntity(CoordenadorDTO coordenadorDTO);
}
