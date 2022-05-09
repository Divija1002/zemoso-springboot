package com.example.springproject.demo.dto;

import lombok.Data;

@Data
public class CustomerDto
{
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String address;

    private String city;

    private String state;

    private String zip;

    private int userid;
}
