package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.*;
import com.example.springproject.demo.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.Order;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Test
    @Order(1)
    @Commit
    void saveOrderTest()
    {
        Category category=new Category("meat");
        categoryRepository.save(category);
        Product product=new Product();
        product.setName("chicken");
        product.setWeight(1);
        product.setPrice(200);
        product.setCategory(category);
        productRepository.save(product);
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
        customer=new Customer();
        customer.setFirstName("test1");
        customer.setLastName("test1");
        customer.setEmail("test1@gmail.com");
        customer.setCity("Hyderabad");
        customer.setState("Telangana");
        customer.setAddress("test 1 address");
        customer.setZip("test1");
        customer.setUserid(user);
        customerRepository.save(customer);
        com.example.springproject.demo.entity.Order order=new com.example.springproject.demo.entity.Order();
        order.setId(1);
        order.setQuantity(2);
        order.setCustomerId(customer);
        order.setProductId(product);
        orderRepository.save(order);
        Assertions.assertThat(order.getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    void getOrderTest()
    {
        com.example.springproject.demo.entity.Order order=orderRepository.findById(1).get();
        Assertions.assertThat(order.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfOrderTest()
    {
        List<com.example.springproject.demo.entity.Order> orderList=orderRepository.findAll();
        Assertions.assertThat(orderList.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void updateOrderTest()
    {
        com.example.springproject.demo.entity.Order order=orderRepository.findById(1).get();
        order.setQuantity(3);
        com.example.springproject.demo.entity.Order updatedOrder=orderRepository.findById(1).get();
        Assertions.assertThat(updatedOrder.getQuantity()).isEqualTo(3);
    }

    @Test
    @Order(5)
    void deleteOrderTest()
    {
        orderRepository.deleteById(1);
        Optional<com.example.springproject.demo.entity.Order> result=orderRepository.findById(1);
        com.example.springproject.demo.entity.Order order=null;
        if(result.isPresent())
        {
            order=result.get();
        }
        Assertions.assertThat(order).isNull();
    }

    @Test
    @Order(6)
    void findByCustomerIdTest(){
        assertThat(orderRepository.findByCustomerId(1)).isEqualTo(orderRepository.findAll());
    }

    @Test
    @Order(7)
    void findByCustomerIdAndProductIdTest(){
        assertThat(Optional.of(orderRepository.findByCustomerIdAndProductId(customerRepository.getById(1),productRepository.getById(1))))
                .isEqualTo(orderRepository.findById(1));
    }

}
