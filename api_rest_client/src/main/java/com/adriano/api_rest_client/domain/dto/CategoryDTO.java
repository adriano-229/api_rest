package com.adriano.api_rest_client.domain.dto;

import lombok.Data;

@Data
public class CategoryDTO implements BaseDTO {
    private Long id;
    private String name;

}
