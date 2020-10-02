package com.hafiz.sm.post.dataclass.post;

import com.hafiz.sm.post.core.crud.IdHolder;
import com.hafiz.sm.post.dataclass.common.UserDto;
import com.hafiz.sm.post.entities.Place;
import lombok.Data;

import java.util.UUID;

@Data
public class PostResponseDTO extends IdHolder {
  //TODO Remove userId
  private UUID userId;
  private UserDto user;
  private String description;
  private String visibility;
  //TODO Remove placeId
  private UUID placeId;
  private Place place;
}
