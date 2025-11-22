package com.adriano.api_rest_client.service;

import com.adriano.api_rest_client.domain.dto.ProductDTO;
import com.adriano.api_rest_client.persistence.rest.ProductRestDAO;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends RestCrudService<ProductDTO, Long> {

    public ProductService(ProductRestDAO productRestDAO) {
        super(productRestDAO);
    }
}
