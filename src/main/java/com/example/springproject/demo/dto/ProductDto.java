package com.example.springproject.demo.dto;

import lombok.Data;

@Data
public class ProductDto
{
    private int id;
    private String name;
    private double price;
    private double weight;
    private int categoryId;
    private String categoryName;
}
