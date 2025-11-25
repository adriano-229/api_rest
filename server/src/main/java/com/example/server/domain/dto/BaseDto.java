package com.example.server.domain.dto;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseDto {
    private Long id;
}
