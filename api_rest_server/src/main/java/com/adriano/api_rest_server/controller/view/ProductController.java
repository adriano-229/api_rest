package com.adriano.api_rest_server.controller.view;

import com.adriano.api_rest_server.domain.entity.Product;
import com.adriano.api_rest_server.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController<Product, Long> {

    public ProductController(ProductService productService) {
        super(productService, "Product", "Products");
    }

    @Override
    protected Product createNewInstance() {
        return new Product();
    }
}

