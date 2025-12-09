package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.ProductDto;
import com.example.server.domain.entity.Product;
import com.example.server.service.ProductService;
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
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Operations for managing products")
public class ProductRestController extends BaseRestController<Product, ProductDto, Long> {

    public ProductRestController(ProductService productService) {
        super(productService);
    }

    @Override
    @GetMapping("")
    @Operation(summary = "List products", description = "Retrieve every registered product")
    @ApiResponse(responseCode = "200", description = "Products returned",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class))))
    public ResponseEntity<List<ProductDto>> findAll() {
        return super.findAll();
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get product", description = "Retrieve a product by its identifier")
    @ApiResponse(responseCode = "200", description = "Product found",
            content = @Content(schema = @Schema(implementation = ProductDto.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductDto> findById(
            @Parameter(description = "Product identifier", required = true)
            @PathVariable("id") Long id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("")
    @Operation(summary = "Create product", description = "Persist a new product")
    @ApiResponse(responseCode = "201", description = "Product created",
            content = @Content(schema = @Schema(implementation = ProductDto.class)))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product payload",
            required = true,
            content = @Content(schema = @Schema(implementation = ProductDto.class)))
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        return super.create(dto);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update product", description = "Replace an existing product")
    @ApiResponse(responseCode = "200", description = "Product updated",
            content = @Content(schema = @Schema(implementation = ProductDto.class)))
    @ApiResponse(responseCode = "404", description = "Product not found")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product payload",
            required = true,
            content = @Content(schema = @Schema(implementation = ProductDto.class)))
    public ResponseEntity<ProductDto> update(
            @Parameter(description = "Product identifier", required = true)
            @PathVariable("id") Long id,
            @Valid @RequestBody ProductDto dto) {
        return super.update(id, dto);
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Remove a product permanently")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Product identifier", required = true)
            @PathVariable("id") Long id) {
        return super.delete(id);
    }
}
