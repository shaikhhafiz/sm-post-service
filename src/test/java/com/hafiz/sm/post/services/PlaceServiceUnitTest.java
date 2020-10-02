package com.hafiz.sm.post.services;

import com.hafiz.sm.post.repositories.PlaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlaceServiceUnitTest {

  private PlaceService service;

  @BeforeEach
  public void init() {
    PlaceRepository repository = Mockito.mock(PlaceRepository.class);
    this.service = new PlaceService(repository);
  }

  @Test
  public void serviceInstanceNotNull() {
    Assertions.assertNotNull(service);
  }
}
