package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.entity.Customer;

import java.util.List;

public interface CustomerService
{
    public List<CustomerDto> findAll();

    public CustomerDto findById(int theId);

    public void save(Customer customer);

    public void deleteById(int theId);

    public CustomerDto findCustomerByUserid(int id);

    public CustomerDto entityToDto(Customer customer);

    public Customer dtoToEntity(CustomerDto customerDto);
}
