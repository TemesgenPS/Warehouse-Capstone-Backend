package com.capstone.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_at_time", nullable = false)
    private BigDecimal priceAtTime;

    // Many items → one order
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Many items → one product
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}