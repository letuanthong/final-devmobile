package com.dev.server.exceptions;

import com.dev.server.controllers.BaseAPI.ValueResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ValueResponse> handleException(Exception e) {
        ValueResponse valueResponse = ValueResponse.builder()
                .code(ErrorCode.SERVER_ERROR.getCode())
                .message(ErrorCode.SERVER_ERROR.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.SERVER_ERROR.getStatusCode()).body(valueResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ValueResponse> handleWebException(AppException e) {
        ValueResponse valueResponse = ValueResponse.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build();
        return ResponseEntity.status(e.getErrorCode().getStatusCode()).body(valueResponse);
    }
}
