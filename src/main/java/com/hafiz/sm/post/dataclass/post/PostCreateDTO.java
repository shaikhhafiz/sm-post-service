package com.hafiz.sm.post.dataclass.post;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class PostCreateDTO{
  @NotBlank(message = "User id can't be blank")
  private UUID userId;
  @NotBlank(message = "Description can't be blank")
  private String description;
  @NotBlank(message = "Visibility can't be blank")
  private String visibility;
  @NotBlank(message = "Location can't be blank")
  private UUID placeId;
}
