package com.example.springproject.demo.service;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Product;

import java.util.List;

public interface ProductService
{
    List<ProductDto> findAll();

    ProductDto findById(int theId);

    List<ProductDto> findByCid(int id);

    void save(Product theProduct);

    void deleteById(int theId);

    ProductDto entityToDto(Product product);

    Product dtoToEntity(ProductDto productDto);

    int findCategoryById(int id);

}
