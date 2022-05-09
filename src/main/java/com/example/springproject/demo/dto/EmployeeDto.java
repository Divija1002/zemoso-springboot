package com.example.springproject.demo.dto;

import com.example.springproject.demo.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
public class EmployeeDto
{
    private int id;

    private String firstName;

    private String  lastName;

    private String email;

    private String phoneNumber;

    private int userid;
}
