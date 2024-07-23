package com.easySchedule.backend.utils.jsonConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public abstract class JsonConverter<E extends Enum<E>> implements AttributeConverter<List<E>, String> {

    private final Class<E> enumClass;

    public JsonConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(List<E> attribute) {
        return attribute != null ? Arrays.toString(attribute.toArray()) : null;
    }

    @Override
    public List<E> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        String[] enumStrings = dbData.replaceAll("\\[|\\]|\"", "").split(", ");
        List<E> enums = new ArrayList<>();
        for (String enumString : enumStrings) {
            try {
                enums.add(Enum.valueOf(enumClass, enumString));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return enums;
    }
}
