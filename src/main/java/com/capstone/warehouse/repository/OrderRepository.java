package com.capstone.warehouse.repository;

import com.capstone.warehouse.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(Integer userId);
    List<Order> findByStatus(Order.Status status);

    // Sales reporting query — orders within a date range
    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :start AND :end")
    List<Order> findOrdersBetweenDates(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);

    // Total revenue in a date range — for sales report
    @Query("SELECT SUM(oi.priceAtTime * oi.quantity) FROM OrderItem oi " +
            "WHERE oi.order.orderDate BETWEEN :start AND :end " +
            "AND oi.order.status = 'COMPLETED'")
    Double getTotalRevenueBetweenDates(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);
}