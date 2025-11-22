package com.adriano.api_rest_client.service;

import com.adriano.api_rest_client.domain.dto.CategoryDTO;
import com.adriano.api_rest_client.persistence.rest.CategoryRestDAO;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends RestCrudService<CategoryDTO, Long> {

    public CategoryService(CategoryRestDAO categoryRestDAO) {
        super(categoryRestDAO);
    }
}
