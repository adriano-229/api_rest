package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.BaseDto;
import com.example.server.domain.entity.BaseEntity;
import com.example.server.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseRestController<E extends BaseEntity, D extends BaseDto, ID extends Serializable> {
    protected final BaseService<E, D, ID> baseService;

    protected BaseRestController(BaseService<E, D, ID> baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public  ResponseEntity<List<D>> findAll() {
        List<D> entities = baseService.readAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable ID id) {
        Optional<D> entity = baseService.readById(id);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<D> create(@RequestBody D entity) {
        D saved = baseService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable ID id, @RequestBody D entity) {
        Optional<D> updated = baseService.update(id, entity);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        Optional<D> existing = baseService.readById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
