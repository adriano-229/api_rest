package com.example.server.service;

import com.example.server.domain.dto.ProductDto;
import com.example.server.domain.entity.Brand;
import com.example.server.domain.entity.Product;
import com.example.server.domain.mapper.ProductMapper;
import com.example.server.repository.BrandRepository;
import com.example.server.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService<Product, ProductDto, Long> {

    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, BrandRepository brandRepository) {
        super(productRepository, productMapper);
        this.brandRepository = brandRepository;
    }

    public void nullifyBrand(List<Product> products) {
        products.forEach(product -> {
            product.setBrand(null);
            baseRepository.save(product);
        });
    }

    @Override
    public void beforeCreate(ProductDto dto, Product entity) {
        Brand brand = brandRepository.findById(dto.getBrandId()).orElseThrow();
        entity.setBrand(brand);
    }
}