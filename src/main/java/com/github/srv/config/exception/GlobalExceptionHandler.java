package com.github.srv.config.exception;

import com.github.srv.adapters.inbound.dtos.ErrorResponse;
import com.github.srv.domain.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
      ErrorResponse response = new ErrorResponse(ex.getStatus(), ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
      Map<String, String> errors = new HashMap<>();

      ex.getAllErrors().forEach((error) -> {
        String fieldName = ((FieldError)error).getField();
        String message = error.getDefaultMessage();

        errors.put(fieldName, message);
      });

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

