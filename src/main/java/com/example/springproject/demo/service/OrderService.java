package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.OrderDto;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.Product;

import java.util.List;

public interface OrderService
{
    public List<OrderDto> findAll();

    public OrderDto findById(int theId);

    public void save(Order order);

    public void deleteById(int theId);

    public List<Order> findByCustomerId(int cid);

    public void addOrder(Customer customer, Product product);

    void deleteOrder(Customer customer, Product product);

    void deleteProduct(Customer customer, Product product);

    public OrderDto entityToDto(Order order);

    public Order dtoToEntity(OrderDto orderDto);
}
