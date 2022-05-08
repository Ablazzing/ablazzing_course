package org.example.controller;


import lombok.Builder;
import lombok.Data;

import java.util.Map;


@Data
@Builder
public class BaseResponse {
    private Integer statusCode;
    private String error;
    private String message;
    private Map<String, Object> data;
}
