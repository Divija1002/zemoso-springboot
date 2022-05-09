package com.example.springproject.demo.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="category")
@Data
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cid")
    private int id;

    @Column(name="cname")
    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    private String categoryName;

    public Category()
    {

    }

    public Category(String categoryName)
    {
        this.categoryName=categoryName;
    }

}
