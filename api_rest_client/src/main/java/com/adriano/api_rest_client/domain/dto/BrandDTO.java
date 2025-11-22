package com.adriano.api_rest_client.domain.dto;

import lombok.Data;

@Data
public class BrandDTO implements BaseDTO {
    private Long id;
    private String name;
}
