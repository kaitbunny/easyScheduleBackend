package com.easySchedule.backend.utils.jsonConverter;



import com.easySchedule.backend.domain.model.enums.Periodo;

import jakarta.persistence.Converter;

@Converter
public class JsonConverterPeriodo extends JsonConverter<Periodo> {
    public JsonConverterPeriodo() {
        super(Periodo.class);
    }
}
