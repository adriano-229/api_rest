package com.adriano.api_rest_client.persistence.rest;

import com.adriano.api_rest_client.domain.dto.CategoryDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryRestDAO {

    private final RestTemplate restTemplate;

    public CategoryRestDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CategoryDTO getCategory(Long id) {
        CategoryDTO category = restTemplate.getForObject("/categories/{id}", CategoryDTO.class, id);
        if (category != null) {
            category.setConsumerDescription("retrieved-via-rest");
        }
        return category;
    }

    public List<CategoryDTO> listCategories() {
        return restTemplate.exchange(
                "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CategoryDTO>>() {
                }
        ).getBody();
    }

    public void createCategory(CategoryDTO category) {
        restTemplate.postForObject("/categories", category, CategoryDTO.class);
    }

    public void updateCategory(Long id, CategoryDTO category) {
        restTemplate.put("/categories/{id}", category, id);
        getCategory(id);
    }

    public void deleteCategory(Long id) {
        restTemplate.delete("/categories/{id}", id);
    }
}
