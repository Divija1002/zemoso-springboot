package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.OrderDto;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.repository.OrderRepository;
import com.example.springproject.demo.service.OrderService;
import com.example.springproject.demo.service.implementation.OrderServiceImpl;
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
 class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService orderService=new OrderServiceImpl(orderRepository,modelMapper);

    public Order order1;

    private ArrayList<Order> list;

    Product product;

    Customer customer1;

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
        Category category1=new Category("Fruits");
        category1.setId(1);
        product=new Product("Mango",150);
        product.setId(1);
        product.setWeight(1);
        product.setCategory(category1);
        order1=new Order();
        order1.setId(1);
        order1.setQuantity(2);
        order1.setCustomerId(customer1);
        order1.setProductId(product);
        list = new ArrayList<Order>();
        list.add(order1);
    }

    @Test
    void findAllTest(){
        when(orderRepository.findAll()).thenReturn(list);
        List<OrderDto> orderList=orderService.findAll();
        assertThat(orderList).hasSize(1);
        assertThat(orderList.get(0).getProductId()).isEqualTo(1);
        assertThat(orderList.get(0).getCustomerId()).isEqualTo(1);
        assertThat(orderList.get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void findByIdtest(){
        when(orderRepository.findById(1)).thenReturn(Optional.of(order1));
        OrderDto orderDto=orderService.findById(1);
        assertThat(orderDto.getProductId()).isEqualTo(1);
        assertThat(orderDto.getCustomerId()).isEqualTo(1);
        assertThat(orderDto.getQuantity()).isEqualTo(2);
    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            orderService.findById(3);
        });
    }

    @Test
    void saveTest(){
        orderService.save(order1);
        verify(orderRepository,times(1)).save(order1);
    }

    @Test
    void deleteById(){
        orderService.deleteById(1);
        verify(orderRepository,times(1)).deleteById(1);
    }

    @Test
    void findByCustomerIdTest(){
        when(orderRepository.findByCustomerId(1)).thenReturn(list);
        List<Order> orderList=orderService.findByCustomerId(1);
        assertThat(orderList).hasSize(1);
        assertThat(orderList.get(0).getProductId()).isEqualTo(product);
        assertThat(orderList.get(0).getCustomerId()).isEqualTo(customer1);
        assertThat(orderList.get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void addOrderTest(){
        when(orderRepository.findByCustomerIdAndProductId(customer1,product)).thenReturn(order1);
        orderService.addOrder(customer1,product);
        assertThat(order1.getQuantity()).isEqualTo(3);
        verify(orderRepository,times(1)).findByCustomerIdAndProductId(customer1,product);
        verify(orderRepository,times(1)).save(order1);
    }

    @Test
    void deleteOrderTest(){
        when(orderRepository.findByCustomerIdAndProductId(customer1,product)).thenReturn(order1);
        orderService.deleteOrder(customer1,product);
        verify(orderRepository,times(1)).delete(order1);
    }

    @Test
    void deleteProductTest(){
        when(orderRepository.findByCustomerIdAndProductId(customer1,product)).thenReturn(order1);
        orderService.deleteProduct(customer1,product);
        assertThat(order1.getQuantity()).isEqualTo(1);
        verify(orderRepository,times(1)).findByCustomerIdAndProductId(customer1,product);
        verify(orderRepository,times(1)).save(order1);
        orderService.deleteProduct(customer1,product);
        verify(orderRepository,times(1)).delete(order1);
    }

}
