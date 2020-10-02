package com.hafiz.sm.post.dataclass.post;

import com.hafiz.sm.post.core.crud.IdHolder;
import lombok.Data;

import java.util.UUID;

@Data
public class PostUpdateDTO extends IdHolder {
  private UUID userId;
  private String description;
  private String visibility;
  private UUID placeId;
}
