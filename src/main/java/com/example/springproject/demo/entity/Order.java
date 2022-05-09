package com.example.springproject.demo.entity;

import com.example.springproject.demo.entity.Customer;
import com.example.springproject.demo.entity.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "orders")
@Data
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cust_id")
    private Customer customerId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pid")
    private Product productId;
}
