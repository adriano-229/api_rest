package com.example.server.domain.dto;

import com.example.server.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BrandDto extends BaseDto {
    private String name;
}
