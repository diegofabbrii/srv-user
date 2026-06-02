package com.github.srv.domain.services;

import com.github.srv.adapters.inbound.dtos.LoginRequest;
import com.github.srv.adapters.inbound.dtos.LoginResponse;
import com.github.srv.config.jwt.JwtService;
import com.github.srv.domain.exceptions.InvalidCredentialsException;
import com.github.srv.domain.model.User;
import com.github.srv.ports.inbound.services.AuthServicePort;
import com.github.srv.ports.outbound.repository.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements AuthServicePort {
  private final UserRepositoryPort userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByUsername(request.username())
        .orElseThrow(InvalidCredentialsException::new);

    if (!passwordEncoder.matches(request.password(), user.password())) {
      throw new InvalidCredentialsException();
    }

    String token = jwtService.generateToken(user.username(), user.role().toString());

    return new LoginResponse(token);
  }
}
