package com.adriano.api_rest_client.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @JsonProperty("name")
    private String nameCategory;

    private String consumerDescription;

}
