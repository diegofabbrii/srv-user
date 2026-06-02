package com.github.srv.adapters.inbound.dtos;

import com.github.srv.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank(message = "O nome de usuário é obrigatório.")
    String username,

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Informe o email no padrão correto.")
    String email,

    @NotBlank(message = "A senha é obrigatória.")
    String password,

    Role role
) {}
