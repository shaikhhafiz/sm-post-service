package com.hafiz.sm.post.dataclass.common;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
  private UUID id;
  private String username;
  private String fullName;
}
