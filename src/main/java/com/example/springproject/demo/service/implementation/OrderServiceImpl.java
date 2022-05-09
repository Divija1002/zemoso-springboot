package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.OrderDto;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.repository.OrderRepository;
import com.example.springproject.demo.repository.ProductRepository;
import com.example.springproject.demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService
{
    private OrderRepository orderRepository;

    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,ModelMapper modelMapper)
    {
        this.orderRepository=orderRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<OrderDto> findAll() {
        List<Order> orderList=orderRepository.findAll();
        List<OrderDto> orderDtoList=new ArrayList<>();
        for(Order tempOrder:orderList)
        {
            OrderDto orderDto=entityToDto(tempOrder);
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @Override
    public OrderDto findById(int theId) {
        Optional<Order> result = orderRepository.findById(theId);
        Order order;
        if(result.isPresent())
        {
            order=result.get();
        }
        else
        {
            throw new RuntimeException("order not found");
        }
        OrderDto orderDto=entityToDto(order);
        return orderDto;
    }

    @Override
    public void save(Order order)
    {
        orderRepository.save(order);
    }

    @Override
    public void deleteById(int theId) {
        orderRepository.deleteById(theId);
    }

    @Override
    public List<Order> findByCustomerId(int cid) {
        return orderRepository.findByCustomerId(cid);
    }

    @Override
    public void addOrder(Customer customer, Product product)
    {
        Order order=orderRepository.findByCustomerIdAndProductId(customer, product);
        if(order!=null)
        {
            int quantity=order.getQuantity()+1;
            order.setQuantity(quantity);
        }
        else
        {
            order=new Order();
            order.setCustomerId(customer);
            order.setProductId(product);
            order.setQuantity(1);
        }
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Customer customer, Product product) {
        Order order=orderRepository.findByCustomerIdAndProductId(customer, product);
        orderRepository.delete(order);
    }

    @Override
    public void deleteProduct(Customer customer, Product product) {
        Order order=orderRepository.findByCustomerIdAndProductId(customer, product);
        int quantity=order.getQuantity()-1;
        if (quantity==0)
        {
            orderRepository.delete(order);
            return;
        }
        order.setQuantity(quantity);
        orderRepository.save(order);
    }

    @Override
    public OrderDto entityToDto(Order order) {
        OrderDto orderDto=new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setCustomerId(order.getCustomerId().getId());
        orderDto.setProductId(order.getProductId().getId());
        return orderDto;
    }

    @Override
    public Order dtoToEntity(OrderDto orderDto) {
        Order order=modelMapper.map(orderDto,Order.class);
        return order;
    }
}
