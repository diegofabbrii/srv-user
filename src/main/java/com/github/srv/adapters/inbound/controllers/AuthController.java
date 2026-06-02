package com.github.srv.adapters.inbound.controllers;

import com.github.srv.adapters.inbound.dtos.LoginRequest;
import com.github.srv.adapters.inbound.dtos.LoginResponse;
import com.github.srv.ports.inbound.services.AuthServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthServicePort authService;

  public AuthController(AuthServicePort authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

}
                                                                                                                                            