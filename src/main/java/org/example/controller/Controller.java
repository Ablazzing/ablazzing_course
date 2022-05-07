package org.example.controller;

import java.util.Map;

public interface Controller {
    default BaseResponse createSuccessfulResponse(String message, Map<String, Object> data) {
        return BaseResponse.builder()
                .statusCode(200)
                .message(message)
                .data(data)
                .build();
    }

    default BaseResponse createFailureResponse(Exception e) {
        return BaseResponse.builder()
                .statusCode(500)
                .errorMessage(e.getMessage())
                .build();
    }
}
