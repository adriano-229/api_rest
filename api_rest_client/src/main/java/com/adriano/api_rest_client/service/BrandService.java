package com.adriano.api_rest_client.service;

import com.adriano.api_rest_client.domain.dto.BrandDTO;
import com.adriano.api_rest_client.persistence.rest.BrandRestDAO;
import org.springframework.stereotype.Service;

@Service
public class BrandService extends RestCrudService<BrandDTO, Long> {

    public BrandService(BrandRestDAO brandRestDAO) {
        super(brandRestDAO);
    }
}
