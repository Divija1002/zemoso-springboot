package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.service.CategoryService;
import com.example.springproject.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("admin/category")
public class CategoryController
{
    Logger logger=Logger.getLogger(CategoryController.class.getName());
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listCategory(Model model)
    {
        List<CategoryDto> categoryList=categoryService.findAll();
        model.addAttribute("category",categoryList);
        return "category/category-list";
    }

    @GetMapping("/add")
    public String addCategory(Model model)
    {
        Category category=new Category();
        model.addAttribute("category",category);
        return "category/category-form";
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "category/category-form";
        try {

            categoryService.save(category);
            return "redirect:/admin/category/list";
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            return "category/category-form";
        }
    }

    @GetMapping("/update")
    public String updateCategory(@RequestParam("cid") int theId,Model model)
    {
        CategoryDto theCategory=categoryService.findById(theId);
        model.addAttribute("category",theCategory);
        return "category/category-form";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam("cid") int theId)
    {
        categoryService.deleteById(theId);
        return "redirect:/admin/category/list";
    }

}
