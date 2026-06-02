package com.github.srv.adapters.outbound.repository;

import com.github.srv.adapters.outbound.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByUsername(String username);
  Optional<UserEntity> findByEmail(String email);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);

}
