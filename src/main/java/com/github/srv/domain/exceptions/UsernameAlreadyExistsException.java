package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends BusinessException {

    public UsernameAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }

    public UsernameAlreadyExistsException() {
        super("O nome de usuário já está cadastrado.", HttpStatus.BAD_REQUEST);
    }

}
