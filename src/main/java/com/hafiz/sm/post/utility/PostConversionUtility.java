package com.hafiz.sm.post.utility;

import com.hafiz.sm.post.core.crud.ConversionUtility;
import com.hafiz.sm.post.dataclass.post.PostResponseDTO;
import com.hafiz.sm.post.dataclass.common.UserDto;
import com.hafiz.sm.post.entities.Post;
import com.hafiz.sm.post.feignclient.AuthenticationServiceClient;
import com.hafiz.sm.post.services.PlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostConversionUtility extends ConversionUtility<Post, PostResponseDTO> {

    private final AuthenticationServiceClient authClient;
    private final PlaceService placeService;

    PostConversionUtility(ModelMapper modelMapper, AuthenticationServiceClient authClient,
                          PlaceService placeService) {
        super(modelMapper, Post.class, PostResponseDTO.class);
        this.authClient = authClient;
        this.placeService = placeService;
    }

    public List<PostResponseDTO> getDtoList(List<Post> entities) {
        List<PostResponseDTO> posts = getPostResponseDTOS(entities);
        Set<UUID> userIds = getUserIds(posts);
        Map<UUID, UserDto> users = getUserDtoMap(userIds);
        setDtos(posts, users);
        return posts;
    }

    private void setDtos(List<PostResponseDTO> posts, Map<UUID, UserDto> users) {
        this.setUserDto(users, posts);
        this.setPlaceDto(posts);
    }

    private void setPlaceDto(List<PostResponseDTO> posts) {
        posts.forEach(post -> {
            post.setPlace(placeService.getById(post.getPlaceId()).get());
        });
    }

    private List<PostResponseDTO> getPostResponseDTOS(List<Post> entities) {
        return entities
                  .stream()
                  .map(Optional::of).map(this::getDto).map(Optional::get)
                  .collect(Collectors.toList());
    }

    private Map<UUID, UserDto> getUserDtoMap(Set<UUID> userIds) {
        return authClient.getUsersByIds(userIds)
                  .stream()
                  .collect(Collectors.toMap(UserDto::getId, user -> user));
    }

    private Set<UUID> getUserIds(List<PostResponseDTO> posts) {
        return posts
                  .stream()
                  .map(PostResponseDTO::getUserId)
                  .collect(Collectors.toSet());
    }

    private void setUserDto(Map<UUID, UserDto> users, List<PostResponseDTO> posts){
        posts.forEach(post -> {
            UserDto user = users.get(post.getUserId());
            if(user != null) post.setUser(user);
        });
    }
}
