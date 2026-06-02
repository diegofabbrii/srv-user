package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }

    public UserNotFoundException() {
        super("Usuário não encontrado.", HttpStatus.NOT_FOUND);
    }

}
