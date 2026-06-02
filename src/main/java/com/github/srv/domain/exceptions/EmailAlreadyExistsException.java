package com.github.srv.domain.exceptions;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends BusinessException {

    public EmailAlreadyExistsException() {
        super("O email informado já está cadastrado.", HttpStatus.BAD_REQUEST);
    }

    public EmailAlreadyExistsException(String message, HttpStatus status) {
        super(message, status);
    }

}
