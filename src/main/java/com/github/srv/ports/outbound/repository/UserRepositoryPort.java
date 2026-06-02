package com.github.srv.ports.outbound.repository;

import com.github.srv.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

  User save(User user);
  Optional<User> findById(String id);
  Optional<User> findByEmail(String email);
  Optional<User> findByUsername(String username);
  Boolean existsByEmail(String email);
  Boolean existsByUsername(String username);
  List<User> findAll();

}
