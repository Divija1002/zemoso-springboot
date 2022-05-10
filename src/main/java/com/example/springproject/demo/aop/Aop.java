package com.example.springproject.demo.aop;

import com.example.springproject.demo.dto.ProductDto;
import com.example.springproject.demo.service.ProductService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class Aop
{
    @Autowired
    private ProductService productService;

@Before(value = "execution(* com.example.springproject.demo.service.implementation.CategoryServiceImpl.deleteById(..))&&args(theId)",
                argNames = "theId")
    public void before(int theId)
    {
        List<ProductDto> productDtoList=productService.findByCid(theId);
        for(ProductDto productDto:productDtoList)
        {
            productService.deleteById(productDto.getId());
        }
    }
}
