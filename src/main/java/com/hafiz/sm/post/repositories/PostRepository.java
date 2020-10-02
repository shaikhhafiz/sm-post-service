package com.hafiz.sm.post.repositories;

import com.hafiz.sm.post.core.crud.ICrudRepository;
import com.hafiz.sm.post.entities.Post;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends ICrudRepository<Post, UUID> {
  List<Post> findAllByVisibility(String visibility);
  List<Post> findAllByUserId(UUID userId);
}
