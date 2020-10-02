package com.hafiz.sm.post.controllers;

import com.hafiz.sm.post.core.constants.EndPoint;
import com.hafiz.sm.post.core.crud.CrudController;
import com.hafiz.sm.post.dataclass.post.PostCreateDTO;
import com.hafiz.sm.post.dataclass.post.PostResponseDTO;
import com.hafiz.sm.post.dataclass.post.PostUpdateDTO;
import com.hafiz.sm.post.entities.Post;
import com.hafiz.sm.post.services.PostService;
import com.hafiz.sm.post.utility.PostConversionUtility;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(EndPoint.POST_URL)
public class PostController extends CrudController<Post, PostResponseDTO, PostCreateDTO, PostUpdateDTO> {

    private final PostService service;
    private final PostConversionUtility conversionUtility;

    PostController(PostService service, PostConversionUtility conversionUtility) {
        super(service, conversionUtility);
        this.service = service;
        this.conversionUtility = conversionUtility;
    }

    //TODO replace hard coded endpoint
    @GetMapping("by-user-id/{userId}")
    public List<PostResponseDTO> getList(@PathVariable UUID userId) {
        return conversionUtility.getDtoList(service.getListByUserId(userId));
    }

}
