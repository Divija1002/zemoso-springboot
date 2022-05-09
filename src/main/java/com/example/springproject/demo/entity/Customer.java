package com.example.springproject.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="customer")
@Data
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private int id;

    @NotEmpty(message = "is required")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "is required")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "is required")
    @Pattern(regexp = "([a-zA-Z0-9_.-]+)@([a-zA-Z0-9]+)([\\.])([a-zA-Z]+)",message = "incorrect email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "is required")
    @Column(name = "address")
    private String address;

    @NotEmpty(message = "is required")
    @Column(name = "city")
    private String city;

    @NotEmpty(message = "is required")
    @Column(name = "state")
    private String state;

    @NotEmpty(message = "is required")
    @Column(name = "zip")
    private String zip;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid")
    private User userid;
}
