package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.CustomerRepository;
import com.example.springproject.demo.service.CustomerService;
import com.example.springproject.demo.service.implementation.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService customerService=new CustomerServiceImpl(customerRepository,modelMapper);

    private Customer customer1;

    private Customer customer2;

    private ArrayList<Customer> list;

    @BeforeEach
    void setMockOutput(){
        Role role=new Role();
        role.setId(2);
        role.setName("ROLE_CUSTOMER");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        User user1=new User();
        user1.setId(1);
        user1.setUsername("test1");
        user1.setPassword("test1");
        user1.setRoles(roleSet);
        customer1=new Customer();
        customer1.setId(1);
        customer1.setFirstName("test1");
        customer1.setLastName("test1");
        customer1.setEmail("test1@gmail.com");
        customer1.setCity("Hyderabad");
        customer1.setState("Telangana");
        customer1.setAddress("test 1 address");
        customer1.setZip("test1");
        customer1.setUserid(user1);
        User user2=new User();
        user2.setId(2);
        user2.setUsername("test2");
        user2.setPassword("test2");
        user2.setRoles(roleSet);
        customer2=new Customer();
        customer2.setId(2);
        customer2.setFirstName("test2");
        customer2.setLastName("test2");
        customer2.setEmail("test2@gmail.com");
        customer2.setCity("Hyderabad");
        customer2.setState("Telangana");
        customer2.setAddress("test 2 address");
        customer2.setZip("test2");
        customer2.setUserid(user2);
        list = new ArrayList<Customer>();
        list.add(customer1);
        list.add(customer2);
    }

    @Test
    void findAllTest(){
        when(customerRepository.findAll()).thenReturn(list);
        List<CustomerDto> customerList=customerService.findAll();
        assertThat(customerList).hasSize(2);
        assertThat(customerList.get(0).getEmail()).isEqualTo("test1@gmail.com");
        assertThat(customerList.get(1).getEmail()).isEqualTo("test2@gmail.com");
    }




}
