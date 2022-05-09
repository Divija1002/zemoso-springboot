package com.example.springproject.demo;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.entity.Product;
import com.example.springproject.demo.service.ProductService;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class aop
{
    @Autowired
    private ProductService productService;

@Before(value = "execution(* com.example.springproject.demo.service.implementation.CategoryServiceImpl.deleteById(..))&&args(theId)",
                argNames = "theId")
public void before(int theId)
{
    List<ProductDto> productDtoList=productService.findByCid(theId);
    //System.out.println(productDtoList);
    for(ProductDto productDto:productDtoList)
    {
        productService.deleteById(productDto.getId());
    }
}
}
