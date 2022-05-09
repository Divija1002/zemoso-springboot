package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.entity.Category;

import java.util.List;

public interface CategoryService
{
    public List<CategoryDto> findAll();

    public CategoryDto findById(int theId);

    public void save(Category theCategory);

    public void deleteById(int theId);

    public CategoryDto entityToDto(Category category);

    public Category dtoToEntity(CategoryDto categoryDto);

}
