package com.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;

    @Min(1)
    private double price;

    private int stock;
    private String imageUrl;

   
    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

   
}