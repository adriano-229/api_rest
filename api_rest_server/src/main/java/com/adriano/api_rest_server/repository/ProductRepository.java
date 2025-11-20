package com.adriano.api_rest_server.repository;

import com.adriano.api_rest_server.domain.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
}
