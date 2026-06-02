package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends BusinessException {

  public InvalidCredentialsException() {
    super("Credenciais invalidas.", HttpStatus.UNAUTHORIZED);
  }

  public InvalidCredentialsException(String message, HttpStatus status) {
    super(message, status);
  }

}

