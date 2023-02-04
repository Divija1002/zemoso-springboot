package com.example.springproject.demo.repositoryTests;

import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.repository.CategoryRepository;
import com.example.springproject.demo.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = ProductRepositoryTests.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTests
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Order(1)
    void saveProductTest()
    {
        Category category=new Category("meat");
        categoryRepository.save(category);
        Product product=new Product();
        product.setName("chicken");
        product.setWeight(1);
        product.setPrice(200);
        product.setCategory(category);
        productRepository.save(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    void getProductTest()
    {
        Product product=productRepository.findById(1).get();
        Assertions.assertThat(product.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    void getListOfProductTest()
    {
        List<Product> productList=productRepository.findAll();
        Assertions.assertThat(productList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    void updateProductTest()
    {
        Product product=productRepository.findById(1).get();
        product.setName("fish");
        Product updatedProduct=productRepository.findById(1).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("fish");
    }

    @Test
    @Order(5)
    void deleteProductTest()
    {
        productRepository.deleteById(1);
        Optional<Product> result=productRepository.findById(1);
        Product product=null;
        if(result.isPresent())
        {
            product=result.get();
        }
        Assertions.assertThat(product).isNull();
    }
}
