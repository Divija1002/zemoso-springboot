package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.entity.Category;

import java.util.List;

public interface CategoryService
{
    List<CategoryDto> findAll();

    CategoryDto findById(int theId);

    void save(Category theCategory);

    void deleteById(int theId);

    CategoryDto entityToDto(Category category);

    Category dtoToEntity(CategoryDto categoryDto);

}
