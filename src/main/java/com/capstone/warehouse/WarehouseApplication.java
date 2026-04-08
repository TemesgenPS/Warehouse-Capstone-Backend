package com.capstone.warehouse;

import com.capstone.warehouse.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class WarehouseApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("warehousePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword("password");
        user.setRole(User.Role.USER);

        em.persist(user);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}