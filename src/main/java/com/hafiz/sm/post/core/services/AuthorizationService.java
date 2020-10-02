package com.hafiz.sm.post.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hafiz.sm.post.dataclass.OAuthTokenDTO;
import com.hafiz.sm.post.exceptions.ExceptionHolders;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class AuthorizationService {

  @Value("${service.feign.client.auth-service}")
  private String authServiceRootUrl;

  @Autowired
  private ObjectMapper objectMapper;

  public boolean validateToken(String token) throws IOException {
    URI uri = this.getURI(token);
    final HttpUriRequest request = new HttpPost(uri);
    final HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
    String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
    //TODO Handle this token info for later use
    OAuthTokenDTO tokenDTO = objectMapper.readValue(responseBody, OAuthTokenDTO.class);
    this.validateToken(tokenDTO);
    return true;
  }

  private void validateToken(OAuthTokenDTO tokenDTO) {
    if(tokenDTO.getAuthorities() == null) throw new ExceptionHolders.InvalidRequestException("Invalid token");
  }

  private URI getURI(String token) {
    return URI.create(authServiceRootUrl + "oauth/check_token/?token=" + token.replace("Bearer ", ""));
  }
}
