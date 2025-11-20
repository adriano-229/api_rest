package com.adriano.api_rest_server.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity {
    private String name;
    private double price;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
}
