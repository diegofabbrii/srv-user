package com.github.srv.adapters.inbound.controllers;

import com.github.srv.adapters.inbound.dtos.UpdateRoleRequest;
import com.github.srv.adapters.inbound.dtos.UserRequest;
import com.github.srv.adapters.inbound.dtos.UserResponse;
import com.github.srv.adapters.inbound.dtos.UserUpdateRequest;
import com.github.srv.ports.inbound.services.UserServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserServicePort userService;

  public UserController(UserServicePort userService) {
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {
    return ResponseEntity.ok(userService.createUser(request));
  }

  @GetMapping()
  public ResponseEntity<List<UserResponse>> findAllUsers() {
    List<UserResponse> users = userService.findAllUsers();

    return ResponseEntity.ok(users);
  }

  @GetMapping("{id}")
  public ResponseEntity<UserResponse> findUserById(@PathVariable String id) {
    UserResponse user = userService.findUserById(id);

    return ResponseEntity.ok(user);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("{id}/deactivate")
  public ResponseEntity<Void> deactivateUser(@PathVariable String id) {
    userService.deactivateUser(id);

    return ResponseEntity.noContent().build();
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("{id}/activate")
  public ResponseEntity<Void> activateUser(@PathVariable String id) {
    userService.activateUser(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}")
  public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateRequest request) {
    UserResponse updatedUser = userService.updateUser(id, request);

    return ResponseEntity.ok(updatedUser);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping("{id}/update-role")
  public ResponseEntity<Void> updateRole(@PathVariable String id, @Valid @RequestBody UpdateRoleRequest roleRequest) {
    userService.updateRole(id, roleRequest);

    return ResponseEntity.noContent().build();
  }

}
