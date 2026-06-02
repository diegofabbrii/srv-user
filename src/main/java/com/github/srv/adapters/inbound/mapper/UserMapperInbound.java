package com.github.srv.adapters.inbound.mapper;

import com.github.srv.adapters.inbound.dtos.UserResponse;
import com.github.srv.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperInbound {

  @Mapping(target = "id", source = "id")
  @Mapping(target = "username", source = "username")
  @Mapping(target = "email", source = "email")
  @Mapping(target = "role", source = "role")
  @Mapping(target = "status", source = "status")
  @Mapping(target = "createdAt", source = "createdAt")
  UserResponse toResponse(User user);

}
