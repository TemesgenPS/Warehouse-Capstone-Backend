package com.capstone.warehouse.service;

import com.capstone.warehouse.entity.Inventory;
import com.capstone.warehouse.entity.Order;
import com.capstone.warehouse.entity.OrderItem;
import com.capstone.warehouse.repository.InventoryRepository;
import com.capstone.warehouse.repository.OrderRepository;
import com.capstone.warehouse.repository.ProductRepository;
import com.capstone.warehouse.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        InventoryRepository inventoryRepository,
                        UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> getOrdersByUser(Integer userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> getOrdersByStatus(Order.Status status) {
        return orderRepository.findByStatus(status);
    }

    // Sales reporting feature
    public List<Order> getOrdersByDateRange(LocalDateTime start, LocalDateTime end) {
        return orderRepository.findOrdersBetweenDates(start, end);
    }

    public Double getRevenueByDateRange(LocalDateTime start, LocalDateTime end) {
        Double revenue = orderRepository.getTotalRevenueBetweenDates(start, end);
        return revenue != null ? revenue : 0.0;
    }

    // ✅ Create order — automatically deducts stock from inventory
    @Transactional
    public Order createOrder(Order order) {
        // Validate user exists
        userRepository.findById(order.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);

        // For each item: validate product, set price, deduct inventory
        for (OrderItem item : order.getItems()) {
            var product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProduct().getId()));

            // Deduct stock
            Inventory inventory = inventoryRepository.findByProductId(product.getId())
                    .orElseThrow(() -> new RuntimeException("No inventory for product: " + product.getId()));

            if (inventory.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            inventory.setQuantity(inventory.getQuantity() - item.getQuantity());
            inventory.setLastUpdated(LocalDateTime.now());
            inventoryRepository.save(inventory);

            // Lock in price at time of order
            item.setPriceAtTime(product.getPrice());
            item.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public Order updateStatus(Integer id, Order.Status status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }
}