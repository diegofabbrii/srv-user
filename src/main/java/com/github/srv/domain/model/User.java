package com.github.srv.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  private UUID id;
  private String username;
  private String email;
  private String password;
  private LocalDateTime cretedAt;

}
