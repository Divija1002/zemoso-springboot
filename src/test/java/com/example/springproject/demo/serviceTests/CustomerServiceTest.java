package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.CustomerDto;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CustomerServiceTest {

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

    @Test
    void findByIdtest(){
        when(customerRepository.findById(1)).thenReturn(Optional.of(customer1));
        CustomerDto categoryDto=customerService.findById(1);
        assertThat(categoryDto.getEmail()).isEqualTo("test1@gmail.com");

        when(customerRepository.findById(2)).thenReturn(Optional.of(customer2));
        CustomerDto categoryDto1=customerService.findById(2);
        assertThat(categoryDto1.getEmail()).isEqualTo("test2@gmail.com");

    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            customerService.findById(3);
        });
    }

    @Test
    void saveTest(){
        customerService.save(customer1);
        verify(customerRepository,times(1)).save(customer1);
    }

    @Test
    void deleteById(){
        customerService.deleteById(1);
        verify(customerRepository,times(1)).deleteById(1);
    }
    @Test
    void findCustomerByUseridTest(){
        when(customerRepository.findByUserid(1)).thenReturn(customer1);
        CustomerDto customer=customerService.findCustomerByUserid(1);
        assertThat(customer.getFirstName()).isEqualTo("test1");
    }

}
