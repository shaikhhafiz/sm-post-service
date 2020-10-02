package com.hafiz.sm.post.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hafiz.sm.post.entities.Post;
import com.hafiz.sm.post.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired private PostRepository repository;

  private static UUID postOid = UUID.fromString("2317de7b-c1a9-48b4-b805-0220b43749c6");

  @Test
  public void Get_Post_List_Returns_List_With_Single_Entry() throws Exception {
    //Arrange, act and assert
    mvc.perform(get("/posts")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(status().isOk()).andReturn();
  }

  @Test
  public void Get_Post_By_Id_Return_Single_Entry() throws Exception {
    //Arrange
    Post post = repository.findById(postOid).get();

    //Act and assert
    mvc.perform(get("/posts/" + post.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
  }

  @Test
  @Transactional
  public void Create_Post_Returns_Create_Status() throws Exception {
    //Arrange
    Post post = this.buildPost();

    //Act and assert
    mvc.perform(post("/posts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(post)))
        .andDo(print())
        .andExpect(status().isCreated())
        .andReturn();
  }

  @Test
  @Transactional
  public void Update_Post_Returns_Success_Status() throws Exception {
    //Arrange
    Post post = repository.findById(postOid).get();
    post.setDescription(post.getDescription() + " updated");

    //Act and assert
    mvc.perform(put("/posts/" + post.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(post)))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  @Transactional
  public void Delete_Post_By_Id_Return_Success_Status() throws Exception {
    //Arrange
    Post post = repository.findById(postOid).get();

    //Act and assert
    mvc.perform(delete("/posts/" + post.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();
  }

  private Post buildPost() {
    Post post = new Post();
    post.setVisibility("public");
    post.setDescription("Tour description for integration testing");
    post.setPlaceId(UUID.fromString("8f52565f-9adc-48d5-871e-3839790f27b9"));
    post.setUserId(UUID.fromString("0acfc622-a823-4fbc-a158-f24210be2499"));
    return post;
  }
}
