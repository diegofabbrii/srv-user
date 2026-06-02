package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyDeactivatedException extends BusinessException {

  public UserAlreadyDeactivatedException() {
    super("Usuário já está desativado.", HttpStatus.CONFLICT);
  }

}

