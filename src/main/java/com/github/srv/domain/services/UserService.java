package com.github.srv.domain.services;

import com.github.srv.adapters.inbound.dtos.UpdateRoleRequest;
import com.github.srv.adapters.inbound.dtos.UserRequest;
import com.github.srv.adapters.inbound.dtos.UserResponse;
import com.github.srv.adapters.inbound.dtos.UserUpdateRequest;
import com.github.srv.adapters.inbound.mapper.UserMapperInbound;
import com.github.srv.domain.enums.Role;
import com.github.srv.domain.exceptions.EmailAlreadyExistsException;
import com.github.srv.domain.exceptions.InvalidCredentialsException;
import com.github.srv.domain.exceptions.UserNotFoundException;
import com.github.srv.domain.exceptions.UsernameAlreadyExistsException;
import com.github.srv.domain.model.User;
import com.github.srv.ports.inbound.services.UserServicePort;
import com.github.srv.ports.outbound.repository.UserRepositoryPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepository;
  private final UserMapperInbound userMapperInbound;
  private final PasswordEncoder passwordEncoder;

  public UserService(
      UserRepositoryPort userRepository,
      UserMapperInbound userMapperInbound,
      PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.userMapperInbound = userMapperInbound;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserResponse createUser(UserRequest userRequest) {
    validateEmailAndUsername(userRequest.email(), userRequest.username());

    UserRequest hashedRequest = new UserRequest(
        userRequest.username(),
        userRequest.email(),
        passwordEncoder.encode(userRequest.password()),
        userRequest.role()
    );

    User user = User.createUser(hashedRequest);
    User savedUser = userRepository.save(user);

    return userMapperInbound.toResponse(savedUser);
  }

  @Override
  public void deactivateUser(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    user = user.deactivateUser();

    userRepository.save(user);
  }

  @Override
  public void activateUser(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    user = user.activateUser();

    userRepository.save(user);
  }

  @Override
  public UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    validateEmailAndUsername(userUpdateRequest.email(), userUpdateRequest.username());

    UserUpdateRequest hashedUpdateRequest = userUpdateRequest.password() != null
        ? new UserUpdateRequest(
            userUpdateRequest.username(),
            userUpdateRequest.email(),
            passwordEncoder.encode(userUpdateRequest.password()))
        : userUpdateRequest;

    User updatedUser = user.updateUser(hashedUpdateRequest);
    User response = userRepository.save(updatedUser);

    return userMapperInbound.toResponse(response);
  }

  @Override
  public void updateRole(String id, Role role) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    user = user.updateRole(role);

    userRepository.save(user);
  }

  @Override
  public UserResponse findUserById(String id) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    return userMapperInbound.toResponse(user);
  }

  @Override
  public List<UserResponse> findAllUsers() {
      List<User> users = userRepository.findAll();

      return users.stream().map(userMapperInbound::toResponse).toList();
  }

  @Override
  public void updateRole(String id, UpdateRoleRequest roleRequest) {
    User user = userRepository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    user = user.updateRole(roleRequest.role());

    userRepository.save(user);
  }

  private void validateEmailAndUsername(String email, String username) {
    Boolean emailExists = userRepository.existsByEmail(email);
    Boolean usernameExists = userRepository.existsByUsername(username);

    if (emailExists || usernameExists) {
      throw new InvalidCredentialsException("Nome de usuário ou e-mail já cadastrado.", HttpStatus.BAD_REQUEST);
    }
  }

}
