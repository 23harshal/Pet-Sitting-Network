package com.petSitting.petSitting.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.Map;
@Converter
public class MapToJsonConverter implements AttributeConverter<Map<String, BigDecimal>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(Map<String, BigDecimal> map) {
        try{
            return objectMapper.writeValueAsString(map);
        }
        catch (Exception e){
            throw new IllegalArgumentException("can not covert" , e);
        }
    }

    @Override
    public Map<String, BigDecimal> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, BigDecimal>>() {});
        }
        catch (Exception e){
            throw new IllegalArgumentException("can not convert", e);
        }
    }
}
