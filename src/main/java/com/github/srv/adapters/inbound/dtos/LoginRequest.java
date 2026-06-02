package com.github.srv.adapters.inbound.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "Informe o nome de usuário")
    String username,

    @NotBlank(message = "Informe a senha")
    String password
) {}
