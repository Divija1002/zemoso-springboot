package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.repository.CustomerRepository;
import com.example.springproject.demo.repository.RoleRepository;
import com.example.springproject.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class CustomerRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    @Order(1)
    @Commit
    void saveCustomerTest()
    {
        Role role=new Role();
        role.setName("ROLE_CUSTOMER");
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        roleRepository.save(role);
        User user=new User();
        user.setUsername("test1");
        user.setPassword("test1");
        user.setRoles(roleSet);
        userRepository.save(user);
        Customer customer=new Customer();
        customer.setFirstName("test2");
        customer.setLastName("test2");
        customer.setEmail("test2@gmail.com");
        customer.setCity("Hyderabad");
        customer.setState("Telangana");
        customer.setAddress("test 2 address");
        customer.setZip("test2");
        customer.setUserid(user);
        customerRepository.save(customer);
        Assertions.assertThat(customer.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void getCustomerTest()
    {
        Customer customer=customerRepository.findById(1).get();
        Assertions.assertThat(customer.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfCustomerTest()
    {
        List<Customer> customerList=customerRepository.findAll();
        Assertions.assertThat(customerList.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void updateCustomerTest()
    {
        Customer customer=customerRepository.findById(1).get();
        customer.setEmail("test3@gmail.com");
        Customer updatedCustomer=customerRepository.findById(1).get();
        Assertions.assertThat(updatedCustomer.getEmail()).isEqualTo("test3@gmail.com");
    }

    @Test
    @Order(5)
    void deleteCustomerTest()
    {
        customerRepository.deleteById(1);
        Optional<Customer> result=customerRepository.findById(1);
        Customer customer=null;
        if(result.isPresent())
        {
            customer=result.get();
        }
        Assertions.assertThat(customer).isNull();
    }

    @Test
    @Order(6)
    void findByUseridTest(){
        Assertions.assertThat(Optional.of(customerRepository.findByUserid(1))).isEqualTo(customerRepository.findById(1));
    }

}
