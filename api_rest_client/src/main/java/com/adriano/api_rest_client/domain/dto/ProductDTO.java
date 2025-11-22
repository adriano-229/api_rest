package com.adriano.api_rest_client.domain.dto;

import lombok.Data;

@Data
public class ProductDTO implements BaseDTO {
    private Long id;
    private String name;
    private double price;
    private CategoryDTO category;
    private BrandDTO brand;
}
