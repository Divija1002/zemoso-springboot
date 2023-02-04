package com.example.springproject.demo.serviceTests;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.repository.CategoryRepository;
import com.example.springproject.demo.service.CategoryService;
import com.example.springproject.demo.service.implementation.CategoryServiceImpl;
import com.example.springproject.demo.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService=new CategoryServiceImpl(categoryRepository);

    private Category category1;

    private  Category category2;

    private ArrayList<Category> list;
    @BeforeEach
    void setMockOutput(){
        category1=new Category("Fruits");
        category2=new Category("vegetables");
        list = new ArrayList<Category>();
        list.add(category1);
        list.add(category2);
    }

    @Test
    void findAllTest(){
        when(categoryRepository.findAll()).thenReturn(list);
        List<CategoryDto> categoryList=categoryService.findAll();
        assertThat(categoryList).hasSize(2);
        assertThat(categoryList.get(0).getCategoryName()).isEqualTo("Fruits");
        assertThat(categoryList.get(1).getCategoryName()).isEqualTo("vegetables");
    }

    @Test
    void findByIdtest(){
        when(categoryRepository.findById(1)).thenReturn(Optional.of(category1));
        CategoryDto categoryDto=categoryService.findById(1);
        assertThat(categoryDto.getCategoryName()).isEqualTo("Fruits");

        when(categoryRepository.findById(2)).thenReturn(Optional.of(category2));
        CategoryDto categoryDto1=categoryService.findById(2);
        assertThat(categoryDto1.getCategoryName()).isEqualTo("vegetables");

    }

    @Test
    void findByIdtestFail(){
        assertThrows(RuntimeException.class,()->{
            categoryService.findById(3);
        });
    }

    @Test
    void saveTest(){
        categoryService.save(category1);
        verify(categoryRepository,times(1)).findAll();
        verify(categoryRepository,times(1)).save(category1);
    }

    @Test
    void saveTestFail(){
        when(categoryRepository.findAll()).thenReturn(list);
        assertThrows(RuntimeException.class,()->{
            categoryService.save(category1);
        });
    }

    @Test
    void deleteById(){
        categoryService.deleteById(1);
        verify(categoryRepository,times(1)).deleteById(1);
    }

}
