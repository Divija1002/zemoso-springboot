package com.example.springproject.demo;

import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryRepositoryTests
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Order(1)
    public void saveCategoryTest()
    {
        Category category=new Category("meat");
        categoryRepository.save(category);
        Assertions.assertThat(category.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getCategoryTest()
    {
        Category category=categoryRepository.findById(1).get();
        Assertions.assertThat(category.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfCategoryTest()
    {
        List<Category> categoryList=categoryRepository.findAll();
        Assertions.assertThat(categoryList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateCategoryTest()
    {
        Category category=categoryRepository.findById(1).get();
        category.setCategoryName("pulses");
        Category updatedCategory=categoryRepository.save(category);
        Assertions.assertThat(updatedCategory.getCategoryName()).isEqualTo("pulses");
    }

    @Test
    @Order(5)
    public void deleteById()
    {
        categoryRepository.deleteById(1);
        Optional<Category> result=categoryRepository.findById(1);
        Category category=null;
        if(result.isPresent())
        {
            category=result.get();
        }
        Assertions.assertThat(category).isNull();
    }
}
