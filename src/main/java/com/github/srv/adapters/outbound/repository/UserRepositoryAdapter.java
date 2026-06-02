package com.github.srv.adapters.outbound.repository;

import com.github.srv.adapters.outbound.entity.UserEntity;
import com.github.srv.adapters.outbound.mapper.UserMapperOutbound;
import com.github.srv.domain.model.User;
import com.github.srv.ports.outbound.repository.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

  private final UserJpaRepository repository;
  private final UserMapperOutbound userMapperOutbound;

  public UserRepositoryAdapter(UserJpaRepository repository, UserMapperOutbound userMapperOutbound) {
    this.repository = repository;
    this.userMapperOutbound = userMapperOutbound;
  }

  @Override
  public User save(User user) {
    UserEntity response = repository.save(userMapperOutbound.toEntity(user));

    return userMapperOutbound.toDomain(response);
  }

  @Override
  public Optional<User> findById(String id) {
    Optional<UserEntity> user = repository.findById(UUID.fromString(id));

    return user.map(userMapperOutbound::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return repository.findByEmail(email)
        .map(userMapperOutbound::toDomain);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username)
        .map(userMapperOutbound::toDomain);
  }

  @Override
  public Boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }

  @Override
  public Boolean existsByUsername(String username) {
    return repository.existsByUsername(username);
  }

  @Override
  public List<User> findAll() {
    return repository.findAll()
        .stream()
        .map(userMapperOutbound::toDomain)
        .toList();
  }

}
