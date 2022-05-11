package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.entity.Customer;

import java.util.List;

public interface CustomerService
{
    List<CustomerDto> findAll();

    CustomerDto findById(int theId);

    void save(Customer customer);

    void deleteById(int theId);

    CustomerDto findCustomerByUserid(int id);

    CustomerDto entityToDto(Customer customer);

    Customer dtoToEntity(CustomerDto customerDto);
}
