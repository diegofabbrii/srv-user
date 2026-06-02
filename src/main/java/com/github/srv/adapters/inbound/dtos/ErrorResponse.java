package com.github.srv.adapters.inbound.dtos;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {}
