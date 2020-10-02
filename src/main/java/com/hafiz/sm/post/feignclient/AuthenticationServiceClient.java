package com.hafiz.sm.post.feignclient;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.hafiz.sm.post.dataclass.common.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.*;

@FeignClient(name = "AUTH-SERVICE", url = "${service.feign.client.auth-service}")
public interface AuthenticationServiceClient {

    @PostMapping("users/by-id-set")
    List<UserDto> getUsersByIds(Set<UUID> ids);

    @RequestMapping
    Map<String, String>  checkToken(URI baseUri);
}
