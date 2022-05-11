package com.example.springproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RestController
{
    @GetMapping("/user")
    public String userDashboard()
    {
        return "user";
    }

    @GetMapping("/admin")
    public String adminDashboard()
    {
        return "admin";
    }

}
