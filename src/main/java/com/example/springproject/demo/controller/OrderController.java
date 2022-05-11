package com.example.springproject.demo.controller;

import com.example.springproject.demo.dto.CustomerDto;
import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.*;
import com.example.springproject.demo.dto.UserDto;
import com.example.springproject.demo.security.CustomUserDetails;
import com.example.springproject.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @RequestMapping("/cart")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        List<Order> orderList=orderService.findByCustomerId(customerDto.getId());
        double totalCost=0;
        for(Order tempOrder:orderList)
        {
            totalCost=totalCost+((tempOrder.getProductId().getPrice())*(tempOrder.getQuantity()));
        }
        totalCost=Math.round(totalCost*100)/100.0;
        model.addAttribute("cart",orderList);
        model.addAttribute("total",totalCost);
        return "shopping-cart";
    }

    @RequestMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int id,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        Customer customer=customerService.dtoToEntity(customerDto);
        ProductDto productDto=productService.findById(id);
        Product product=productService.dtoToEntity(productDto);
        orderService.addOrder(customer,product);
        int cid= productService.findCategoryById(id);
        return "redirect:/user/products/"+cid;
    }

    @RequestMapping("/update/{id}")
    public String updateCart(@PathVariable("id") int id,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        Customer customer=customerService.dtoToEntity(customerDto);
        ProductDto productDto=productService.findById(id);
        Product product=productService.dtoToEntity(productDto);
        orderService.addOrder(customer,product);
        return "redirect:/order/cart";
    }

    @RequestMapping("/update/remove/{id}")
    public String deleteProduct(@PathVariable("id") int id,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        Customer customer=customerService.dtoToEntity(customerDto);
        ProductDto productDto=productService.findById(id);
        Product product=productService.dtoToEntity(productDto);
        orderService.deleteProduct(customer,product);
        return "redirect:/order/cart";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id,@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        Customer customer=customerService.dtoToEntity(customerDto);
        ProductDto productDto=productService.findById(id);
        Product product=productService.dtoToEntity(productDto);
        orderService.deleteOrder(customer,product);
        return "redirect:/order/cart";
    }

    @RequestMapping("/checkout")
    public String checkout(@AuthenticationPrincipal CustomUserDetails userDetails,Model model)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        List<Order> orderList=orderService.findByCustomerId(customerDto.getId());
        model.addAttribute("order",orderList);
        return "checkout";
    }

    @RequestMapping("/confirm")
    public String deleteOrders(@AuthenticationPrincipal CustomUserDetails userDetails)
    {
        String username= userDetails.getUsername();
        User user=userService.findByUsername(username);
        CustomerDto customerDto=customerService.findCustomerByUserid(user.getId());
        List<Order> orderList=orderService.findByCustomerId(customerDto.getId());
        for(Order tempOrder:orderList)
        {
            orderService.deleteById(tempOrder.getId());
        }
        return "order-confirmation";
    }

}
