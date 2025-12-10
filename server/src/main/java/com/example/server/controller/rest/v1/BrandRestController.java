package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.BrandDto;
import com.example.server.domain.entity.Brand;
import com.example.server.service.BrandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController extends BaseRestController<Brand, BrandDto, Long> {

    public BrandRestController(BrandService brandService) {
        super(brandService);
    }


    @Tag(name = "Brands")
    @GetMapping("")
    public ResponseEntity<List<BrandDto>> findAll() {
        return super.findAll();
    }

    @Tag(name = "Brands")
    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> findById(@PathVariable("id") Long id) {
        return super.findById(id);
    }

    @Tag(name = "Brands")
    @PostMapping("")
    public ResponseEntity<BrandDto> create(@Valid @RequestBody BrandDto dto) {
        return super.create(dto);
    }

    @Tag(name = "Brands")
    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> update(@PathVariable("id") Long id, @Valid @RequestBody BrandDto dto) {
        return super.update(id, dto);
    }

    @Tag(name = "Brands")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        return super.delete(id);
    }
}
