package com.github.srv.ports.inbound.services;

import com.github.srv.adapters.inbound.dtos.UpdateRoleRequest;
import com.github.srv.adapters.inbound.dtos.UserRequest;
import com.github.srv.adapters.inbound.dtos.UserResponse;
import com.github.srv.adapters.inbound.dtos.UserUpdateRequest;
import com.github.srv.domain.enums.Role;

import java.util.List;

public interface UserServicePort {

  UserResponse createUser(UserRequest userRequest);
  void deactivateUser(String id);
  void activateUser(String id);
  UserResponse updateUser(String id, UserUpdateRequest userUpdateRequest);
  void updateRole(String id, Role role);
  UserResponse findUserById(String id);
  List<UserResponse> findAllUsers();
  void updateRole(String id, UpdateRoleRequest roleRequest);

}
