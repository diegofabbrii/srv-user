package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserInactiveException extends BusinessException {

  public UserInactiveException() {
    super("Usuario desativado.", HttpStatus.FORBIDDEN);
  }

  public UserInactiveException(String message, HttpStatus status) {
    super(message, status);
  }

}

