package com.example.springproject.demo.dto;

import lombok.Data;

@Data
public class OrderDto
{
    private int id;

    private int quantity;

    private int customerId;

    private int productId;
}
