package com.example.springproject.demo.controller;

import com.example.springproject.demo.entity.Employee;
import com.example.springproject.demo.entity.Role;
import com.example.springproject.demo.entity.User;
import com.example.springproject.demo.service.EmployeeService;
import com.example.springproject.demo.service.RoleService;
import com.example.springproject.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/employee")
@SessionAttributes(value = "user")
public class EmployeeController
{
    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user=new User();
        model.addAttribute("user", user);
        return "employee-signup";
    }

    @PostMapping("/process-register")
    public String processRegister(@Valid@ModelAttribute("user") User user, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Set<Role> roles=new HashSet<>();
        roles.add(roleService.findById(1));
        user.setRoles(roles);
        userService.save(user);
        model.addAttribute("user",user);
        model.addAttribute("employee",new Employee());
        return "employee-register";
    }

    @RequestMapping("/save-employee")
    public String saveCustomerRegistration(@ModelAttribute("user") User user, @Valid@ModelAttribute("employee") Employee employee, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "employee-register";
        }
        employee.setUserid(user);
        employeeService.save(employee);
        return "redirect:/admin";
    }
}
