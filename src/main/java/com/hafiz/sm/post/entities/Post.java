package com.hafiz.sm.post.entities;

import com.hafiz.sm.post.core.crud.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Table(name = "post")
public class Post extends BaseEntity {
    private UUID userId;
    private String description;
    private String visibility;
    private UUID placeId;
}
