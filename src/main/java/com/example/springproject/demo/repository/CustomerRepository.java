package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
    @Query(value = "select * from customer c where c.userid = ?1",nativeQuery = true)
    public Customer findByUserid(int userid);
}
