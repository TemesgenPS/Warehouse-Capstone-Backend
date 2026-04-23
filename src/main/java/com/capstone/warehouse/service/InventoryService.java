package com.capstone.warehouse.service;

import com.capstone.warehouse.entity.Inventory;
import com.capstone.warehouse.entity.Product;
import com.capstone.warehouse.repository.InventoryRepository;
import com.capstone.warehouse.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            ProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Integer id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with id: " + id));
    }

    public Inventory getInventoryByProductId(Integer productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found for product id: " + productId));
    }

    // Low-stock alert feature
    public List<Inventory> getLowStockItems() {
        return inventoryRepository.findLowStockItems();
    }

    public Inventory createInventory(Inventory inventory) {
        // Validate product exists
        Product product = productRepository.findById(inventory.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Make sure inventory doesn't already exist for this product
        if (inventoryRepository.findByProductId(product.getId()).isPresent()) {
            throw new RuntimeException("Inventory already exists for product id: " + product.getId());
        }

        inventory.setProduct(product);
        inventory.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public Inventory updateStock(Integer id, Integer newQuantity) {
        Inventory inventory = getInventoryById(id);
        inventory.setQuantity(newQuantity);
        inventory.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Integer id, Inventory updated) {
        Inventory existing = getInventoryById(id);
        existing.setQuantity(updated.getQuantity());
        existing.setReorderLevel(updated.getReorderLevel());
        existing.setLastUpdated(LocalDateTime.now());
        return inventoryRepository.save(existing);
    }

    public void deleteInventory(Integer id) {
        inventoryRepository.deleteById(id);
    }
}