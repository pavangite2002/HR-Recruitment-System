package com.example.employeemodel.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdateUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode getJsonNode(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> targetClass) {
        try {
            return objectMapper.readValue(content, targetClass);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
