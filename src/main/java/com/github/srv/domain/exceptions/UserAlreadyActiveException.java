package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyActiveException extends BusinessException {

  public UserAlreadyActiveException() {
    super("Usuário já está ativo.", HttpStatus.CONFLICT);
  }

}

