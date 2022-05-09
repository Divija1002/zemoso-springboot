package com.example.springproject.demo.controller;

import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.service.CustomerService;
import com.example.springproject.demo.service.RoleService;
import com.example.springproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
@SessionAttributes(value = "user")
public class LoginController
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/login")
    public String showMyLoginPage()
    {
        //return "plain-login";
        //return "fancy-login";
        return "fancy-login-new";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied()
    {
        return "access-denied";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user=new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping("/process_register")
    public String processRegister(User user, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.findById(2));
        user.setRoles(roles);
        userService.save(user);
        model.addAttribute("customer",new Customer());
        return "customer-details";
    }

    @RequestMapping("/save-user")
    public String saveCustomerRegistration(@ModelAttribute("user") User user, Customer customer)
    {
        customer.setUserid(user);
        customerService.save(customer);
        return "register_success";
    }

}
