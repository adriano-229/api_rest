package com.adriano.api_rest_client.controller.view;

import com.adriano.api_rest_client.domain.dto.BrandDTO;
import com.adriano.api_rest_client.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/brands")
public class BrandController extends BaseController<BrandDTO, Long> {

    public BrandController(BrandService brandService) {
        super(brandService, "Brand", "brands");
    }

    @Override
    protected BrandDTO createNewInstance() {
        return new BrandDTO();
    }
}
