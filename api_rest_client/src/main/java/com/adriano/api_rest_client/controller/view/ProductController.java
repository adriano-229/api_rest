package com.adriano.api_rest_client.controller.view;

import com.adriano.api_rest_client.domain.dto.BrandDTO;
import com.adriano.api_rest_client.domain.dto.CategoryDTO;
import com.adriano.api_rest_client.domain.dto.ProductDTO;
import com.adriano.api_rest_client.service.BrandService;
import com.adriano.api_rest_client.service.CategoryService;
import com.adriano.api_rest_client.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController<ProductDTO, Long> {

    private final CategoryService categoryService;
    private final BrandService brandService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             BrandService brandService) {
        super(productService, "Product", "products");
        this.categoryService = categoryService;
        this.brandService = brandService;
    }

    @Override
    protected ProductDTO createNewInstance() {
        return new ProductDTO();
    }

    @Override
    protected void customizeFormModel(Model model, ProductDTO entity) {
        List<CategoryDTO> categories = categoryService.findAll();
        List<BrandDTO> brands = brandService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
    }
}
