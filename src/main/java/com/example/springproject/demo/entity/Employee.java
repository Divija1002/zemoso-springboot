package com.example.springproject.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="employee")
@Data
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empid")
    private int id;

    @NotEmpty
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty
    @Column(name = "lastname")
    private String  lastName;

    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "phoneno")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "userid")
    private User userid;
}
