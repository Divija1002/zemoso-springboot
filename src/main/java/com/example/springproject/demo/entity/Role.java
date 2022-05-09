package com.example.springproject.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Data
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int id;

    @Column(name = "name")
    private String name;
}
