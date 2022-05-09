package com.example.springproject.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class RestController
{
    @RequestMapping("/user")
    public String userDashboard()
    {
        return "user";
    }

    @RequestMapping("/admin")
    public String adminDashboard()
    {
        return "admin";
    }

}
