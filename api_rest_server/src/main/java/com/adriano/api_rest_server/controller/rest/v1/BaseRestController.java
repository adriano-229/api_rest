package com.adriano.api_rest_server.controller.rest.v1;

import com.adriano.api_rest_server.domain.entity.BaseEntity;
import com.adriano.api_rest_server.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public abstract class BaseRestController<T extends BaseEntity, ID> {

    protected final BaseService<T, ID> baseService;

    public BaseRestController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }

    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        List<T> entities = baseService.findAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        Optional<T> entity = baseService.findById(id);
        return entity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        T saved = baseService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        T updated = baseService.update(id, entity);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        Optional<T> existing = baseService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        baseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
