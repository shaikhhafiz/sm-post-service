package com.hafiz.sm.post.dataclass.place;

import com.hafiz.sm.post.core.crud.IdHolder;
import lombok.Data;

@Data
public class PlaceResponseDTO extends IdHolder {
  private String name;
}
