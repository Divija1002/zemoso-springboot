package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.repository.ProductRepository;
import com.example.springproject.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService
{
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository)
    {
    this.productRepository=productRepository;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products=productRepository.findAll();
        List<ProductDto> productDtos=new ArrayList<>();
        for(Product tempProduct:products)
        {
            ProductDto productDto=entityToDto(tempProduct);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);
        Product theProduct=null;
        if(result.isPresent())
        {
            theProduct=result.get();
        }
        else
        {
            throw new RuntimeException("product not found id: "+theId);
        }
        return entityToDto(theProduct);
    }

    @Override
    public List<ProductDto> findByCid(int id) {
        List<Product> products=productRepository.findByCid(id);
        List<ProductDto> productDtos=new ArrayList<>();
        for(Product tempProduct:products)
        {
            ProductDto productDto=entityToDto(tempProduct);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }

    @Override
    public ProductDto entityToDto(Product product) {
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setWeight(product.getWeight());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryName(product.getCategory().getCategoryName());
        return productDto;
    }

    @Override
    public Product dtoToEntity(ProductDto productDto) {
        Product product=new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setWeight(productDto.getWeight());
        product.setPrice(productDto.getPrice());
        Category category=new Category();
        category.setId(productDto.getCategoryId());
        category.setCategoryName(productDto.getCategoryName());
        product.setCategory(category);
        return product;
    }

    @Override
    public int findCategoryById(int id) {
        return productRepository.findCategoryById(id);
    }


}
