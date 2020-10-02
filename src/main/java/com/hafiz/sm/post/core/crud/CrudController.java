package com.hafiz.sm.post.core.crud;

import com.hafiz.sm.post.exceptions.ExceptionHolders;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class CrudController<E extends BaseEntity, R extends IdHolder, C, U extends IdHolder> {

    private final CrudService<E> service;
    private final ConversionUtility<E, R> conversionUtility;

    @GetMapping
    public List<R> getList() {
        return conversionUtility.getDtoList(service.getList());
    }

    @GetMapping("{id}")
    public R getById(@PathVariable UUID id){
        return conversionUtility.getDto(service.getById(id)).orElse(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public R create(@RequestBody C d) {
        return conversionUtility.getDto(service.create(conversionUtility.buildEntity(Optional.of(d)))).orElse(null);
    }

    @PutMapping("{id}")
    public R update(@RequestBody U d, @PathVariable UUID id) {
        //TODO handle update more wisely. Separate responsibility more wisely
        Optional<E> e = service.getById(id);
        if(!e.isPresent()) throw new ExceptionHolders.ResourceNotFoundException("Post not found with id : " + id);
        d.setId(id);
        e.ifPresent(value -> BeanUtils.copyProperties(d, value));
        return conversionUtility.getDto(service.update(e)).orElse(null);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        service.deleteById(Optional.of(id));
    }
}
