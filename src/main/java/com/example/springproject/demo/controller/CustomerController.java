package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.CategoryDto;
import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.dto.OrderDto;
import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Order;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.security.CustomUserDetails;
import com.example.springproject.demo.service.*;
import com.example.springproject.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class CustomerController
{
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/home")
    public String listCategory(Model model)
    {
        List<CategoryDto> categoryList=categoryService.findAll();
        model.addAttribute("category",categoryList);
        return "user";
    }

    @RequestMapping("/products")
    public String shopProducts(Model model,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        model.addAttribute("product",productService.findAll());
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        UserDto userDto=new UserDto();
        userDto=userService.entityToDto(user);
        CustomerDto customerDto=customerService.findCustomerByUserid(userDto.getId());
        List<Order> order=orderService.findByCustomerId(customerDto.getId());
        model.addAttribute("order",order);
        return "shop-list";
    }

    @RequestMapping("/products/{cid}")
    public String shopProductsByCategory(Model model, @PathVariable int cid,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        model.addAttribute("product",productService.findByCid(cid));
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        UserDto userDto=new UserDto();
        userDto=userService.entityToDto(user);
        CustomerDto customerDto=customerService.findCustomerByUserid(userDto.getId());
        List<Order> order=orderService.findByCustomerId(customerDto.getId());
        model.addAttribute("order",order);
        return "shop-list";
    }

    @RequestMapping("/profile")
    public String  viewProfile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        UserDto userDto=new UserDto();
        userDto=userService.entityToDto(user);
        CustomerDto customerDto=customerService.findCustomerByUserid(userDto.getId());
        model.addAttribute("customer",customerDto);
        return "view-profile";
    }
}
