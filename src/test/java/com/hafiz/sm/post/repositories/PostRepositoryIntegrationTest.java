package com.hafiz.sm.post.repositories;

import com.hafiz.sm.post.entities.Post;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryIntegrationTest {

  @Autowired
  private PostRepository repository;

  @Test
  public void repositoryIsNotNull() {
    assertNotNull(repository);
  }

  @Test
  void findAllByVisibilityPublicReturnsListWithSingleEntry() {
    List<Post> posts = repository.findAllByVisibility("public");
    assertEquals(1, posts.size());
  }

  @Test
  void findAllByVisibilityPrivateReturnsListWithSingleEntry() {
    List<Post> posts = repository.findAllByVisibility("private");
    assertEquals(0, posts.size());
  }
}
