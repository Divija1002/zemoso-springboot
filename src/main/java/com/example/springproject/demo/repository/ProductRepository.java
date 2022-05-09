package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer>
{
    @Query(value = "select * from product p where p.cid = ?1",nativeQuery = true)
    public List<Product> findByCid(int id);
    @Query(value = "select p.cid from product p where p.pid = ?1",nativeQuery = true)
    public int findCategoryById(int id);
}
