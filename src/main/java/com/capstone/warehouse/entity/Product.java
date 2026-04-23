package com.capstone.warehouse.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Many products → one supplier
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // Many products → one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // One-to-one with inventory
    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Inventory inventory;
}