Phase-1: MenuService + OrderService
Repository prepared for YazanLubbad
GitHub: https://github.com/YazanLubbad/microservice.git

Structure:
- menu-service/   -> Spring Boot (Maven) skeleton with H2, MenuController, seed data
- order-service/  -> Spring Boot (Maven) skeleton with H2, OrderController, uses WebClient to validate menu items
- docker-compose.yml

Instructions:
1. Build & run with Docker Compose:
   docker-compose up --build

2. Or run each service with Maven:
   cd menu-service
   mvn spring-boot:run
   cd ../order-service
   mvn spring-boot:run

APIs:
- MenuService (port 8081)
  GET /api/menu           -> list items
  GET /api/menu/<built-in function id>      -> get item by id

- OrderService (port 8082)
  POST /api/orders        -> create order (validates items via MenuService)

Note: These are minimal skeleton projects intended for Phase-1 testing and further development.
