package com.example.server.service;

import com.example.server.domain.dto.BrandDto;
import com.example.server.domain.entity.Brand;
import com.example.server.domain.entity.Product;
import com.example.server.domain.mapper.BrandMapper;
import com.example.server.repository.BrandRepository;
import com.example.server.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService extends BaseService<Brand, BrandDto, Long> {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public BrandService(BrandRepository brandRepository, BrandMapper brandMapper, ProductRepository productRepository, ProductService productService) {
        super(brandRepository, brandMapper);
        this.productRepository = productRepository;
        this.productService = productService;
    }


    @Override
    public void beforeDelete(Long id) {
        List<Product> products = productRepository.findByBrandId(id);
        productService.nullifyBrand(products);
    }
}