# Inventory Management System - Backend

A full-stack backend service for a small business inventory management system. Built with Spring Boot, it provides APIs for managing products, suppliers, stock levels, orders, and reporting.

This system supports role-based access (ADMIN / USER) and is designed to simulate real-world warehouse/inventory operations.

---

## Features

- **Product Management** — Full CRUD with search and filtering  
- **Supplier Management** — Manage supplier contact information  
- **Category Management** — Organize products into categories  
- **Inventory Tracking** — Real-time stock updates  
- **Low-Stock Alerts** — Detects items at or below reorder level  
- **Order Management** — Multi-item orders with automatic stock deduction  
- **Sales Reporting** — Revenue and order analytics by date range  
- **Role-Based Access Control** — ADMIN and USER permissions  
- **Authentication System** — Secure user login and creation  

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 3.2 |
| Security | Spring Security |
| Database | MySQL 8 |
| ORM | Spring Data JPA / Hibernate |
| Build Tool | Maven |

---

## User Stories

- As an admin, I want to create, update, and delete products so I can manage inventory.  
- As an admin, I want to track stock levels so I can prevent shortages.  
- As an admin, I want low-stock alerts so I can reorder items in time.  
- As a user, I want to view available products so I can place orders.  
- As a user, I want to create orders so I can purchase items.  
- As an admin, I want to manage suppliers so vendor data stays accurate.  
- As an admin, I want to categorize products so inventory is organized.  
- As an admin, I want sales reports so I can analyze business performance.  
- As a user, I want to view my order history.  
- As an admin, I want role-based access control for security.  

---

## Database Setup

Create the database:

CREATE DATABASE warehouse_db;

---

## Configuration

Update `src/main/resources/application.properties`:

server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3306/warehouse_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

---

## Running the Backend

Run the application:

mvn spring-boot:run

Backend runs at:
http://localhost:8081

Test endpoint:
http://localhost:8081/api/categories

---

## Key API Endpoints

POST /api/auth/login — Login user  
POST /api/users — Create user  
GET /api/products — Get products  
GET /api/products/search?name= — Search products  
GET /api/inventory/low-stock — Low stock alerts  
POST /api/orders — Create order  
PATCH /api/orders/{id}/status — Update order  
GET /api/orders/report/revenue — Revenue report  

---

## Project Structure

controller/ → REST endpoints  
service/ → Business logic  
repository/ → Database access  
entity/ → Database models  
dto/ → Data transfer objects  
config/ → Security configuration  

---

## Security

- Password encryption using BCrypt  
- Role-based authorization (ADMIN / USER)  
- Protected API routes  

---

## Common Issues

- Port conflict → change server.port  
- MySQL not running → start database service  
- Tables not created → ensure ddl-auto=update  

---

## Future Improvements

- JWT authentication  
- Email notifications for low stock  
- Pagination for large datasets  
- Cloud deployment (AWS / Azure)  
- Unit and integration testing  
