package com.example.springproject.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="employee")
@Data
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private int id;

    @NotEmpty(message = "is required")
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty(message = "is required")
    @Column(name = "lastname")
    private String  lastName;

    @NotEmpty(message = "is required")
    @Pattern(regexp = "([a-zA-Z0-9_.-]+)@([a-zA-Z0-9]+)([\\.])([a-zA-Z]+)",message = "incorrect email")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "is required")
    @Column(name = "phoneno")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid")
    private User userid;
}
