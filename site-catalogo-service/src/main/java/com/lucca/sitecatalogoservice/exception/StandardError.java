package com.lucca.sitecatalogoservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    private List<FieldErrorDetails> fieldErrors;

    @Data
    @AllArgsConstructor
    public static class FieldErrorDetails {
        private String field;
        private String defaultMessage;
    }
}
