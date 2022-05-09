package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.repository.CustomerRepository;
import com.example.springproject.demo.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,ModelMapper modelMapper)
    {
    this.customerRepository=customerRepository;
    this.modelMapper=modelMapper;
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customerList=customerRepository.findAll();
        List<CustomerDto> customerDtoList=new ArrayList<>();
        for(Customer tempCustomer:customerList)
        {
            CustomerDto customerDto=entityToDto(tempCustomer);
            customerDtoList.add(customerDto);
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto findById(int theId) {
        Optional<Customer> result = customerRepository.findById(theId);
        Customer customer;
        if(result.isPresent())
        {
            customer=result.get();
        }
        else
        {
            throw new RuntimeException("customer not found");
        }
        CustomerDto customerDto=entityToDto(customer);
        return customerDto;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

    @Override
    public CustomerDto findCustomerByUserid(int id) {
        Customer customer=customerRepository.findByUserid(id);
        CustomerDto customerDto=entityToDto(customer);
        return customerDto;
    }

    @Override
    public CustomerDto entityToDto(Customer customer) {
        CustomerDto customerDto=new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setAddress(customer.getAddress());
        customerDto.setCity(customer.getCity());
        customerDto.setState(customer.getState());
        customerDto.setZip(customer.getZip());
        customerDto.setUserid(customer.getUserid().getId());
        return customerDto;
    }

    @Override
    public Customer dtoToEntity(CustomerDto customerDto) {
        Customer customer=modelMapper.map(customerDto,Customer.class);
        return customer;
    }

}
