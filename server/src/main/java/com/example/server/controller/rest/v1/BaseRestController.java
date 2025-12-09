package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.BaseDto;
import com.example.server.domain.entity.BaseEntity;
import com.example.server.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// TODO: clean specific controllers, ("") mandatory, review openjdk if it was needed, clean pom's, try cleaning code)

public abstract class BaseRestController<E extends BaseEntity, D extends BaseDto, ID extends Serializable> {
    protected final BaseService<E, D, ID> baseService;

    protected BaseRestController(BaseService<E, D, ID> baseService) {
        this.baseService = baseService;
    }


    public ResponseEntity<List<D>> findAll() {
        List<D> entities = baseService.findAll();
        return ResponseEntity.ok(entities);
    }


    public ResponseEntity<D> findById(@PathVariable("id") ID id) {
        Optional<D> entity = baseService.findById(id);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    public ResponseEntity<D> create(@Valid @RequestBody D entity) {
        D saved = baseService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    public ResponseEntity<D> update(@PathVariable("id") ID id, @Valid @RequestBody D entity) {
        Optional<D> updated = baseService.update(id, entity);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        Optional<D> existing = baseService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
