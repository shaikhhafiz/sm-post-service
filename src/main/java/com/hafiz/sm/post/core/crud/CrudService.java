package com.hafiz.sm.post.core.crud;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class CrudService<E extends BaseEntity> implements ICrudService<E> {

    private final ICrudRepository<E, UUID> repository;

    @Override
    public List<E> getList() {
        return repository.findAll();
    }

    @Override
    public Optional<E> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<E> create(Optional<E> entity) {
        E createdEntity = null;
        entity.ifPresent(e -> e.setId(null));
        if(entity.isPresent()) {
            createdEntity = repository.save(entity.get());
        }
        assert createdEntity != null;
        return Optional.of(createdEntity);
    }

    @Override
    public List<E> createAll(List<E> entities) {
        entities = entities
            .stream()
            .peek(e -> e.setId(null))
            .collect(Collectors.toList());
        return repository.saveAll(entities);
    }

    @Override
    public Optional<E> update(Optional<E> entity) {
        E updatedEntity = null;
        if(entity.isPresent()) {
            updatedEntity = repository.save(entity.get());
        }
        assert updatedEntity != null;
        return Optional.of(updatedEntity);
    }

    @Override
    public void deleteById(Optional<UUID> id) {
        id.ifPresent(repository::deleteById);
    }
}
