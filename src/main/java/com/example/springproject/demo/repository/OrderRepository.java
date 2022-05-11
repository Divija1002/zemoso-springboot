package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer>
{
    @Query(value = "select * from orders o where o.cust_id = ?1",nativeQuery = true)
    List<Order> findByCustomerId(int cid);

    Order findByCustomerIdAndProductId(Customer customer, Product product);
}
