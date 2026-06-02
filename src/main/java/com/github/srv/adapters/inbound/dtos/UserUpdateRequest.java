package com.github.srv.adapters.inbound.dtos;

import com.github.srv.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
    String username,

    @Email(message = "Informe o email no padrão correto.")
    String email,

    String password

) {}
