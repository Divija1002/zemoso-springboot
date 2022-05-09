package com.example.springproject.demo.cart;

import com.example.springproject.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCart
{
    public static List<Product> cartList;
    static
    {
        cartList=new ArrayList<>();
    }
}
