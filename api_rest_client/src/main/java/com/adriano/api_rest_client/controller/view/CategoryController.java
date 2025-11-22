package com.adriano.api_rest_client.controller.view;

import com.adriano.api_rest_client.domain.dto.CategoryDTO;
import com.adriano.api_rest_client.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController<CategoryDTO, Long> {

    public CategoryController(CategoryService categoryService) {
        super(categoryService, "Category", "categories");
    }

    @Override
    protected CategoryDTO createNewInstance() {
        return new CategoryDTO();
    }
}
