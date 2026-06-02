package com.github.srv.adapters.inbound.dtos;

import com.github.srv.domain.enums.Role;
import jakarta.validation.constraints.NotNull;

public record UpdateRoleRequest(
    @NotNull(message = "É obrigatório informar o nível de permissão do usuário.")
    Role role
) {
}
