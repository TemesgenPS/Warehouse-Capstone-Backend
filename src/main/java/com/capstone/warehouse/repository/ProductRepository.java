package com.capstone.warehouse.repository;

import com.capstone.warehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsBySku(String sku);
    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findBySupplierId(Integer supplierId);
    List<Product> findByNameContainingIgnoreCase(String name);
}