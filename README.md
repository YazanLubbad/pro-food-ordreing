# Microservices Project

This project contains two microservices:

1. **Menu Service**
2. **Order Service**

Each service runs independently and exposes REST APIs.

---

## Project Structure

microservice/
├── menu-service/
│ ├── src/main/java/com/menu_service/menu_service/
│ ├── src/main/resources/
│ └── build.gradle
├── order-service/
│ ├── src/main/java/com/order_service/order_service/
│ ├── src/main/resources/
│ └── build.gradle
└── .gitignore

yaml
Copy code

---

## Services Overview

### 1. Menu Service

- **Purpose:** Manage menu items.
- **Stack:** Java, Spring Boot, JPA.
- **Base URL:** `http://localhost:8081`

**Endpoints:**

| Method | Endpoint   | Description            |
| ------ | ---------- | ---------------------- |
| GET    | /menu      | Get all menu items     |
| GET    | /menu/{id} | Get a single menu item |
| POST   | /menu      | Create a new menu item |
| PUT    | /menu/{id} | Update menu item       |
| DELETE | /menu/{id} | Delete menu item       |

---

### 2. Order Service

- **Purpose:** Manage orders.
- **Stack:** Java, Spring Boot, JPA.
- **Base URL:** `http://localhost:8082`

**Endpoints:**

| Method | Endpoint     | Description        |
| ------ | ------------ | ------------------ |
| GET    | /orders      | Get all orders     |
| GET    | /orders/{id} | Get a single order |
| POST   | /orders      | Create a new order |
| PUT    | /orders/{id} | Update an order    |
| DELETE | /orders/{id} | Delete an order    |

---

## Running the Project

1. Make sure **Java 17** is installed.
2. Open terminal in each service folder (`menu-service` and `order-service`) and run:

```bash
./gradlew bootRun
Use Postman or any API client to test the endpoints.

Notes
Each service has its own configuration files (application.properties and application.yml).

Services are independent but can be connected in future for full microservice architecture.

Keep README.md updated with new endpoints or changes.
```
