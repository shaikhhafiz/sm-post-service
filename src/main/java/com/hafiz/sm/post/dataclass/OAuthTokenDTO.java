package com.hafiz.sm.post.dataclass;

import lombok.Data;

import java.util.List;

@Data
public class OAuthTokenDTO {
  private List<String> aud;
  private String user_name;
  private List<String> scope;
  private Boolean active;
  private String exp;
  private List<String> authorities;
  private String jti;
  private String client_id;

}
