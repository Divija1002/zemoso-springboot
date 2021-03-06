package com.example.springproject.demo.service.implementation;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.entity.Category;
import com.example.springproject.demo.repository.CategoryRepository;
import com.example.springproject.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList=categoryRepository.findAll();
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        for(Category tempCategory:categoryList)
        {
            CategoryDto categoryDto=entityToDto(tempCategory);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto findById(int theId) {
        Optional<Category> result=categoryRepository.findById(theId);
        Category category=null;
        if(result.isPresent())
        {
            category=result.get();
        }
        else
        {
            throw new RuntimeException("Did not find category with id: "+theId);
        }
        return entityToDto(category);
    }

    @Override
    public void save(Category theCategory)
    {
        String categoryName= theCategory.getCategoryName();
        List<Category> categoryList=categoryRepository.findAll();
        for(Category tempCategory:categoryList)
        {
            if (tempCategory.getCategoryName().toLowerCase().equals(categoryName.toLowerCase()))
                throw new RuntimeException("category already present");
        }
        categoryRepository.save(theCategory);
    }

    @Override
    public void deleteById(int theId) {
        categoryRepository.deleteById(theId);
    }

    @Override
    public CategoryDto entityToDto(Category category) {
      CategoryDto categoryDto=new CategoryDto();
      categoryDto.setId(category.getId());
      categoryDto.setCategoryName(category.getCategoryName());
      return categoryDto;
    }

    @Override
    public Category dtoToEntity(CategoryDto categoryDto) {
        Category category=new Category();
        category.setId(categoryDto.getId());
        category.setCategoryName(categoryDto.getCategoryName());
        return category;
    }
}
