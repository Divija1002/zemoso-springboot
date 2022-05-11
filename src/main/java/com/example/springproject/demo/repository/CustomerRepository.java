package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer>
{
    @Query(value = "select * from customer c where c.userid = ?1",nativeQuery = true)
    Customer findByUserid(int userid);
}
