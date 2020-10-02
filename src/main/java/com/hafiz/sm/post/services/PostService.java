package com.hafiz.sm.post.services;

import com.hafiz.sm.post.core.crud.CrudService;
import com.hafiz.sm.post.entities.Post;
import com.hafiz.sm.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService extends CrudService<Post> {

    private final PostRepository repository;
    private final String visibility = "public";

    public PostService(PostRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public List<Post> getList() {
        return repository.findAllByVisibility(visibility);
    }

    public List<Post> getListByUserId(UUID userId) {
        return repository.findAllByUserId(userId);
    }
}
