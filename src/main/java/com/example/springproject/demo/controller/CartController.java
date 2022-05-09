package com.example.springproject.demo.controller;

import com.example.springproject.demo.cart.ProductCart;
import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController
{
    @Autowired
    private ProductService productService;

    @RequestMapping("/add/{id}")
    public String addToCart(@PathVariable int id)
    {
        ProductDto productDto=productService.findById(id);
        Product product=productService.dtoToEntity(productDto);
        ProductCart.cartList.add(product);
        return "redirect:/user/products";
    }

    @RequestMapping("/cart-list")
    public String cartList(Model model)
    {
        model.addAttribute("cart",ProductCart.cartList);
        model.addAttribute("total",ProductCart.cartList.stream().mapToDouble(Product::getPrice).sum());
        return "cart";
    }

    @RequestMapping("/delete/{index}")
    public String delete(@PathVariable int index)
    {
        ProductCart.cartList.remove(index);
        return "redirect:/cart/cart-list";
    }

}
