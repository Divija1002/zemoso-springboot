package com.example.springproject.demo.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="product")
@Data
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int id;

    @Column(name = "pname")
    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    private String name;

    @Column(name = "price")
    @NotNull(message = "is required")
    private double price;

    @Column(name="weight")
    @NotNull(message = "is required")
    private double weight;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "cid",referencedColumnName = "cid")
    private Category category;

    public Product()
    {

    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
