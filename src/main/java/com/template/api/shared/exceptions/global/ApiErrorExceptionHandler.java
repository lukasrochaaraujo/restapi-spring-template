package com.template.api.shared.exceptions.global;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiErrorExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiErrorExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleExeption(HttpServletRequest request, Exception ex) {
        logger.error(ex.getMessage(), ex);

        var apiError = ApiError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .method(request.getMethod())
                .url(request.getRequestURL().toString())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
