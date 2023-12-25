package com.example.orderservice.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    private Double price;
    @Column(name = "product_id")
    private Long productId;
    private String currency;
    private Double discount;
    @Column(name = "user_id")
    private Long userId;
}
