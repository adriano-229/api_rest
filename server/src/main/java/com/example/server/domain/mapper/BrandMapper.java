package com.example.server.domain.mapper;

import com.example.server.domain.dto.BrandDto;
import com.example.server.domain.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper extends BaseMapper<Brand, BrandDto> {

}
