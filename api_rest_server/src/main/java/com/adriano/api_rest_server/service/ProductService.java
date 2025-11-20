package com.adriano.api_rest_server.service;

import com.adriano.api_rest_server.domain.entity.Product;
import com.adriano.api_rest_server.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, Long> {

    public ProductService(BaseRepository<Product, Long> repository) {
        super(repository);
    }
}