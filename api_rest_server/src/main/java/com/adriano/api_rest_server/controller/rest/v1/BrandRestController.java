package com.adriano.api_rest_server.controller.rest.v1;

import com.adriano.api_rest_server.domain.entity.Brand;
import com.adriano.api_rest_server.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController extends BaseRestController<Brand, Long> {

    public BrandRestController(BrandService brandService) {
        super(brandService);
    }
}

