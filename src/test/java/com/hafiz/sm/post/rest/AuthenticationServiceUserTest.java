package com.hafiz.sm.post.rest;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


public class AuthenticationServiceUserTest {

  @Test()
  public void User_List_Not_Empty_Expected_From_Auth_Service() throws IOException, JSONException {
    // Arrange
    final HttpUriRequest request = new HttpGet("http://localhost:8081/users/");

    // Act
    final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
    JSONArray users = new JSONArray(responseBody);

    // Assert
    assertAll(
        () -> Assertions.assertTrue(users.length() > 0),
        () -> assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK))
    );
  }
}
