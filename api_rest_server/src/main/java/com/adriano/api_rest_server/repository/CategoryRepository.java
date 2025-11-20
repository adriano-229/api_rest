package com.adriano.api_rest_server.repository;


import com.adriano.api_rest_server.domain.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
