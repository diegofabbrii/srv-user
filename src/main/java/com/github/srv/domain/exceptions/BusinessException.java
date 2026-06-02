package com.github.srv.domain.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BusinessException extends RuntimeException {
  private final HttpStatus status;

  public BusinessException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
}
