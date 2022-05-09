package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.service.CategoryService;
import com.example.springproject.demo.service.ProductService;
import com.example.springproject.demo.entity.Product;
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
@RequestMapping("/admin/product")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String listProduct(Model model)
    {
        List<ProductDto> productList=productService.findAll();
        model.addAttribute("product",productList);
        return "product/product-list";
    }

    @RequestMapping("/add")
    public String addProduct(Model model)
    {
        Product product=new Product();
        model.addAttribute("product",product);
        model.addAttribute("category",categoryService.findAll());
        return "product/product-form";
    }

    @RequestMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("category",categoryService.findAll());
            return "product/product-form";
        }
        productService.save(product);
        return "redirect:/admin/product/list";
    }

    @RequestMapping("/update")
    public String updateProduct(@RequestParam("pid") int theId,Model model)
    {
        ProductDto productdto=productService.findById(theId);
        Product product=productService.dtoToEntity(productdto);
        model.addAttribute("product",product);
        model.addAttribute("category",categoryService.findAll());
        return "product/product-form";
    }

    @RequestMapping("/delete")
    public String deleteProduct(@RequestParam("pid") int theId)
    {
        productService.deleteById(theId);
        return "redirect:/admin/product/list";
    }
}
