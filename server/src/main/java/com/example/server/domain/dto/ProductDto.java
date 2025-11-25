package com.example.server.domain.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDto extends BaseDto {
    private String name;
    private double price;
    private Long brandId;
}
