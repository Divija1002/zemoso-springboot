package com.example.springproject.demo.repository;

import com.example.springproject.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>
{

}
