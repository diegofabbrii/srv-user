package com.github.srv.ports.inbound.services;

import com.github.srv.adapters.inbound.dtos.LoginRequest;
import com.github.srv.adapters.inbound.dtos.LoginResponse;

public interface AuthServicePort {
  LoginResponse login(LoginRequest request);
}
