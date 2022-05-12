package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.service.CategoryService;
import com.example.springproject.demo.service.ProductService;
import com.example.springproject.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/admin/product")
public class ProductController
{
    Logger logger=Logger.getLogger(String.valueOf(ProductController.class));
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String listProduct(Model model)
    {
        List<ProductDto> productList=productService.findAll();
        model.addAttribute("product",productList);
        return "product/product-list";
    }

    @GetMapping("/add")
    public String addProduct(Model model)
    {
        Product product=new Product();
        model.addAttribute("product",product);
        model.addAttribute("category",categoryService.findAll());
        return "product/product-form";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,BindingResult bindingResult,Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("category",categoryService.findAll());
            return "product/product-form";
        }
        try {
            productService.save(product);
            return "redirect:/admin/product/list";
        }
        catch (Exception e)
        {
            logger.info(String.valueOf(e));
            model.addAttribute("category",categoryService.findAll());
            return "product/product-form";
        }
    }

    @GetMapping("/update")
    public String updateProduct(@RequestParam("pid") int theId,Model model)
    {
        ProductDto productdto=productService.findById(theId);
        Product product=productService.dtoToEntity(productdto);
        model.addAttribute("product",product);
        model.addAttribute("category",categoryService.findAll());
        return "product/product-form";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("pid") int theId)
    {
        productService.deleteById(theId);
        return "redirect:/admin/product/list";
    }
}
