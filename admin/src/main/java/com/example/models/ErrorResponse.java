package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private List<Error> errors;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        private String field;
        private String message;
    }
}
