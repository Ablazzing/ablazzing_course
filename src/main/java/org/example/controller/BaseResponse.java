package org.example.controller;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class BaseResponse {
    private Integer statusCode;
    private String errorMessage;
    private String message;
    private Map<String, Object> data;
}
