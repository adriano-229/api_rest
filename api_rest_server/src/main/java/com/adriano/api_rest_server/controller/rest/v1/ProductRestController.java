package com.adriano.api_rest_server.controller.rest.v1;

import com.adriano.api_rest_server.domain.entity.Product;
import com.adriano.api_rest_server.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController extends BaseRestController<Product, Long> {

    public ProductRestController(ProductService productService) {
        super(productService);
    }
}

