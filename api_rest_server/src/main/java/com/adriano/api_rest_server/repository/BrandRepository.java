package com.adriano.api_rest_server.repository;

import com.adriano.api_rest_server.domain.entity.Brand;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends BaseRepository<Brand, Long> {
}
