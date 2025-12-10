package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.ProductDto;
import com.example.server.domain.entity.Product;
import com.example.server.service.ProductService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Schema(implementation = ProductDto.class)

public class ProductRestController extends BaseRestController<Product, ProductDto, Long> {

    public ProductRestController(ProductService productService) {
        super(productService);
    }


    @Tag(name = "Products")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> findAll() {
        return super.findAll();
    }

    @Tag(name = "Products")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id) {
        return super.findById(id);
    }

    @Tag(name = "Products")
    @PostMapping("")
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        return super.create(dto);
    }

    @Tag(name = "Products")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto dto) {
        return super.update(id, dto);
    }

    @Tag(name = "Products")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}