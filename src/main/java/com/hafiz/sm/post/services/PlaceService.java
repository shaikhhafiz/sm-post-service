package com.hafiz.sm.post.services;

import com.hafiz.sm.post.core.crud.CrudService;
import com.hafiz.sm.post.entities.Place;
import com.hafiz.sm.post.repositories.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaceService extends CrudService<Place> {

    public PlaceService(PlaceRepository repository) {
        super(repository);
    }
}
