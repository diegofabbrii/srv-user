package com.github.srv.domain.model;

import com.github.srv.adapters.inbound.dtos.UserRequest;
import com.github.srv.adapters.inbound.dtos.UserUpdateRequest;
import com.github.srv.domain.enums.Role;
import com.github.srv.domain.enums.Status;
import com.github.srv.domain.exceptions.UserAlreadyActiveException;
import com.github.srv.domain.exceptions.UserAlreadyDeactivatedException;
import java.time.LocalDateTime;
import java.util.UUID;

public record User(
    UUID id,
    String username,
    String email,
    String password,
    Role role,
    Status status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static User createUser(UserRequest userRequest) {
    return new User(
        null,
        userRequest.username(),
        userRequest.email(),
        userRequest.password(),
        userRequest.role() != null ? userRequest.role() : Role.USER,
        Status.ACTIVE,
        LocalDateTime.now(),
        LocalDateTime.now()
    );
  }

  public User deactivateUser() {
    if (this.status == Status.DEACTIVATED) {
      throw new UserAlreadyDeactivatedException();
    }

    return new User(
        this.id,
        this.username,
        this.email,
        this.password,
        this.role,
        Status.DEACTIVATED,
        this.createdAt,
        LocalDateTime.now()
    );
  }

  public User activateUser() {
    if (this.status == Status.ACTIVE) {
      throw new UserAlreadyActiveException();
    }

    return new User(
        this.id,
        this.username,
        this.email,
        this.password,
        this.role,
        Status.ACTIVE,
        this.createdAt,
        LocalDateTime.now()
    );
  }

  public Boolean isActive() {
    return this.status == Status.ACTIVE;
  }

  public User updateUser(UserUpdateRequest userUpdateRequest) {
    return new User(
        this.id,
        userUpdateRequest.username() != null ? userUpdateRequest.username() : this.username,
        userUpdateRequest.email() != null ? userUpdateRequest.email() : this.email,
        userUpdateRequest.password() != null ? userUpdateRequest.password() : this.password,
        this.role,
        this.status,
        this.createdAt,
        LocalDateTime.now()
    );
  }

  public User updateRole(Role role) {
    return new User(
        this.id,
        this.username,
        this.email,
        this.password,
        role,
        this.status,
        this.createdAt,
        LocalDateTime.now()
    );
  }

}
