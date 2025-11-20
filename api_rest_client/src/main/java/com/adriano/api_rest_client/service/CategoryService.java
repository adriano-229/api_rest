package com.adriano.api_rest_client.service;

import com.adriano.api_rest_client.domain.dto.CategoryDTO;
import com.adriano.api_rest_client.persistence.rest.CategoryRestDAO;
import org.springframework.stereotype.Service;

import java.util.List;


/*
Service class for managing categories business logic via REST API.
 */
@Service
public class CategoryService {

    private final CategoryRestDAO categoryRestDAO;

    public CategoryService(CategoryRestDAO categoryRestDAO) {
        this.categoryRestDAO = categoryRestDAO;
    }

    public CategoryDTO getCategory(Long id) {
        return categoryRestDAO.getCategory(id);
    }

    public List<CategoryDTO> listCategories() {
        return categoryRestDAO.listCategories();
    }

    public void createCategory(CategoryDTO category) {
        categoryRestDAO.createCategory(category);
    }

    public void updateCategory(Long id, CategoryDTO category) {
        categoryRestDAO.updateCategory(id, category);
    }

    public void deleteCategory(Long id) {
        categoryRestDAO.deleteCategory(id);
    }

}
