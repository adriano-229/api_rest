package com.adriano.api_rest_server.service;

import com.adriano.api_rest_server.domain.entity.Category;
import com.adriano.api_rest_server.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseService<Category, Long> {

    public CategoryService(BaseRepository<Category, Long> repository) {
        super(repository);
    }
}