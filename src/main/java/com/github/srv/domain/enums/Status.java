package com.github.srv.domain.enums;

public enum Status {
  ACTIVE("ativo"),
  DEACTIVATED("desativado");

  private String description;

  private Status(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

}
