package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.OrderDto;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.Product;

import java.util.List;

public interface OrderService
{
    List<OrderDto> findAll();

    OrderDto findById(int theId);

    void save(Order order);

    void deleteById(int theId);

    List<Order> findByCustomerId(int cid);

    void addOrder(Customer customer, Product product);

    void deleteOrder(Customer customer, Product product);

    void deleteProduct(Customer customer, Product product);

    OrderDto entityToDto(Order order);

    Order dtoToEntity(OrderDto orderDto);
}
