package com.easySchedule.backend.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaDTO {
    private Long id;
    private String numero;
    private String andar;
    private String predio;
    private Long escolaId;
    private String escolaNome;
}
