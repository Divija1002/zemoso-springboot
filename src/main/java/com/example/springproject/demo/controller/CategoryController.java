package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.service.CategoryService;
import com.example.springproject.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String listCategory(Model model)
    {
        List<CategoryDto> categoryList=categoryService.findAll();
        model.addAttribute("category",categoryList);
        return "category/category-list";
    }

    @RequestMapping("/add")
    public String addCategory(Model model)
    {
        Category category=new Category();
        model.addAttribute("category",category);
        return "category/category-form";
    }

    @RequestMapping("/save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "category/category-form";
        categoryService.save(category);
        return "redirect:/admin/category/list";
    }

    @RequestMapping("/update")
    public String updateCategory(@RequestParam("cid") int theId,Model model)
    {
        CategoryDto theCategory=categoryService.findById(theId);
        model.addAttribute("category",theCategory);
        return "category/category-form";
    }

    @RequestMapping("/delete")
    public String deleteCategory(@RequestParam("cid") int theId)
    {
        categoryService.deleteById(theId);
        return "redirect:/admin/category/list";
    }

}
