package com.easySchedule.backend.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.easySchedule.backend.model.Periodo;

@Converter
public class JsonConverter implements AttributeConverter<List<Periodo>, String> {

	@Override
	public String convertToDatabaseColumn(List<Periodo> attribute) {
		return attribute != null ? Arrays.toString(attribute.toArray()) : null;
	}

	@Override
	public List<Periodo> convertToEntityAttribute(String dbData) {
	    if (dbData == null) {
	        return null;
	    }
	    String[] periodoStrings = dbData.replaceAll("\\[|\\]", "").split(", ");
	    List<Periodo> periodos = new ArrayList<>();
	    for (String periodoString : periodoStrings) {
	    	try {
	    		periodos.add(Periodo.valueOf(periodoString));
	    	}
	        catch(IllegalArgumentException e) {
	        	System.out.println(e.getMessage());
	        }
	    }
	    return periodos;
	}

}
