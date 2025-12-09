package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.BrandDto;
import com.example.server.domain.entity.Brand;
import com.example.server.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController extends BaseRestController<Brand, BrandDto, Long> {

    public BrandRestController(BrandService brandService) {
        super(brandService);
    }


    @GetMapping("")
    public ResponseEntity<List<BrandDto>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get brand", description = "Retrieve a brand by its identifier")
    @ApiResponse(responseCode = "200", description = "Brand found",
            content = @Content(schema = @Schema(implementation = BrandDto.class)))
    @ApiResponse(responseCode = "404", description = "Brand not found")
    public ResponseEntity<BrandDto> findById(
            @Parameter(description = "Brand identifier", required = true)
            @PathVariable("id") Long id) {
        return super.findById(id);
    }

    @PostMapping("")
    @Operation(summary = "Create brand", description = "Persist a new brand")
    @ApiResponse(responseCode = "201", description = "Brand created",
            content = @Content(schema = @Schema(implementation = BrandDto.class)))
    public ResponseEntity<BrandDto> create(@Valid @RequestBody BrandDto dto) {
        return super.create(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> update(
            @Parameter(description = "Brand identifier", required = true)
            @PathVariable("id") Long id,
            @Valid @RequestBody BrandDto dto) {
        return super.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete brand", description = "Remove a brand permanently")
    @ApiResponse(responseCode = "204", description = "Brand deleted")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Brand identifier", required = true)
            @PathVariable("id") Long id) {
        return super.delete(id);
    }
}
