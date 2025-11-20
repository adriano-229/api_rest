package com.adriano.api_rest_server.controller.view;

import com.adriano.api_rest_server.domain.entity.Brand;
import com.adriano.api_rest_server.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController extends BaseController<Brand, Long> {

    public BrandController(BrandService brandService) {
        super(brandService, "Brand", "Brands");
    }

    @Override
    protected Brand createNewInstance() {
        return new Brand();
    }
}

