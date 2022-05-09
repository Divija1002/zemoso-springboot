package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Product;

import java.util.List;

public interface ProductService
{
    public List<ProductDto> findAll();

    public ProductDto findById(int theId);

    public List<ProductDto> findByCid(int id);

    public void save(Product theProduct);

    public void deleteById(int theId);

    public ProductDto entityToDto(Product product);

    public Product dtoToEntity(ProductDto productDto);

    public int findCategoryById(int id);

}
