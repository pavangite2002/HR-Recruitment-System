package com.example.employeemodel.helper.utils;

import com.example.employeemodel.dto.RestApiResponse;

import java.util.Date;

public class ResponseBuilder {

    public static <T> RestApiResponse<T> success(String message, T data) {
        return RestApiResponse.<T>builder()
                .message(message)
                .data(data)
                .timestamp(new Date())
                .build();
    }

    public static <T> RestApiResponse<T> success(String message) {
        return RestApiResponse.<T>builder()
                .message(message)
                .timestamp(new Date())
                .build();
    }

    public static <T> RestApiResponse<T> error(String message, Object error) {
        return RestApiResponse.<T>builder()
                .message(message)
                .errors(error)
                .timestamp(new Date())
                .build();
    }

    public static <T> RestApiResponse<T> notFound(String message) {
        return RestApiResponse.<T>builder()
                .message(message)
                .timestamp(new Date())
                .build();
    }
}

