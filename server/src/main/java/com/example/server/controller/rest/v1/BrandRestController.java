package com.example.server.controller.rest.v1;

import com.example.server.domain.dto.BrandDto;
import com.example.server.domain.dto.ProductDto;
import com.example.server.domain.entity.Brand;
import com.example.server.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandRestController extends BaseRestController<Brand, BrandDto, Long> {

    public BrandRestController(BrandService brandService) {
        super(brandService);
    }


}

