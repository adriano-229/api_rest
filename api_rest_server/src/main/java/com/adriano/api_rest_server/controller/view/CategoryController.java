package com.adriano.api_rest_server.controller.view;

import com.adriano.api_rest_server.domain.entity.Category;
import com.adriano.api_rest_server.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, Long> {

    public CategoryController(CategoryService categoryService) {
        super(categoryService, "Category", "Categories");
    }

    @Override
    protected Category createNewInstance() {
        return new Category();
    }

}