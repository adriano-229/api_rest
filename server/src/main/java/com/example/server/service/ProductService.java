package com.example.server.service;

import com.example.server.domain.dto.ProductDto;
import com.example.server.domain.entity.Product;
import com.example.server.domain.mapper.BaseMapper;
import com.example.server.domain.mapper.ProductMapper;
import com.example.server.repository.BaseRepository;
import com.example.server.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService<Product, ProductDto, Long> {

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
    }

    public void nullifyBrand(List<Product> products) {
        products.forEach(product -> {
            product.setBrand(null);
            baseRepository.save(product);
        });
    }

    @Override
    public void beforeCreate(ProductDto dto) {
        if (dto.getBrandId() != null) {
            Product product = new Product();
            product.setBrandId(dto.getBrandId());
            super.beforeCreate(product);
        }
    }

/*    @Override
    public void beforeCreate(Product product) {
        brandRepository.findById(product.getBrand().getId())
                .orElseThrow(() -> new IllegalArgumentException("Brand with ID " + product.getBrand().getId() + " " + "does not exist."));
    }

    @Override
    public void beforeUpdate(Long productId, Product newProduct) {
        brandRepository.findById(newProduct.getBrand().getId())
                .orElseThrow(() -> new IllegalArgumentException("Brand with ID " + newProduct.getBrand().getId() + " " + "does not exist."));
    }*/
}