package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.repository.ProductRepository;
import com.example.springproject.demo.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService=new ProductServiceImpl(productRepository);

    private Product product;

    private Category category;

    private List<Product> list;

    @BeforeEach
    void setMockOutput(){
        category=new Category("Fruits");
        category.setId(1);
        product=new Product("mango",150);
        product.setId(1);
        product.setWeight(1);
        product.setCategory(category);
        list=new ArrayList<Product>();
        list.add(product);
    }

    @Test
    void findAllTest(){
        when(productRepository.findAll()).thenReturn(list);
        List<ProductDto> productList=productService.findAll();
        assertThat(productList).hasSize(1);
        assertThat(productList.get(0).getName()).isEqualTo("mango");
        assertThat(productList.get(0).getCategoryName()).isEqualTo("Fruits");
    }

    @Test
    void findByIdtest(){
        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        ProductDto productDto=productService.findById(1);
        assertThat(productDto.getName()).isEqualTo("mango");
        assertThat(productDto.getCategoryName()).isEqualTo("Fruits");

    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            productService.findById(2);
        });
    }

    @Test
    void findByCidTest(){
        when(productRepository.findByCid(1)).thenReturn(list);
        List<ProductDto> productList=productService.findByCid(1);
        assertThat(productList).hasSize(1);
        assertThat(productList.get(0).getName()).isEqualTo("mango");
        assertThat(productList.get(0).getCategoryName()).isEqualTo("Fruits");
    }

    @Test
    void saveTest(){
        productService.save(product);
        verify(productRepository,times(1)).findAll();
        verify(productRepository,times(1)).save(product);
    }

    @Test
    void saveTestFail(){
        when(productRepository.findAll()).thenReturn(list);
        assertThrows(RuntimeException.class,()->{
            productService.save(product);
        });
    }

    @Test
    void deleteById(){
        productService.deleteById(1);
        verify(productRepository,times(1)).deleteById(1);
    }

    @Test
    void findCategoryByIdTest(){
        when(productRepository.findCategoryById(1)).thenReturn(1);
        assertThat(productService.findCategoryById(1)).isEqualTo(1);
    }


}
