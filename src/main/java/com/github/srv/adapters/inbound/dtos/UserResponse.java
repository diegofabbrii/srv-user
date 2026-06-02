package com.github.srv.adapters.inbound.dtos;

import com.github.srv.domain.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
    UUID id,
    String username,
    String email,
    Role role,
    String status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
