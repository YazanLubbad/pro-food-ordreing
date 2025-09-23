Food Ordering System — Phase 1
Repository

Prepared for Yazan Lubbad
GitHub: https://github.com/YazanLubbad/microservice.git

Structure
microservice/
├── menu-service/ # Spring Boot (Maven) with H2, MenuController, seed data
├── order-service/ # Spring Boot (Maven) with H2, OrderController, WebClient validation
└── docker-compose.yml

Build and Run
Option 1: Run with Docker Compose
cd microservice
docker compose -f docker-compose.yml up -d --build

Check logs:

docker compose -f docker-compose.yml logs -f menu-service
docker compose -f docker-compose.yml logs -f order-service

Option 2: Run with Maven (local development)

Open two terminals and run:

Menu Service

cd microservice/menu-service
mvn spring-boot:run

Order Service

cd microservice/order-service
export MENU_BASE_URL=http://localhost:8081
mvn spring-boot:run

APIs
Menu Service (port 8081)

GET /api/menu/items → list all menu items

GET /api/menu/items/{id} → get menu item by id

Order Service (port 8082)

POST /api/orders → create new order

Validates menu items via MenuService before saving

Example Requests

List menu items:

curl http://localhost:8081/api/menu/items

Get a specific item:

curl http://localhost:8081/api/menu/items/1

Create order:

curl -X POST http://localhost:8082/api/orders \
 -H "Content-Type: application/json" \
 -d '{
"customerId": 1,
"items": [
{ "menuItemId": 1, "quantity": 2 },
{ "menuItemId": 2, "quantity": 1 }
]
}'

Notes

Minimal skeleton implementation for Phase 1 testing.

Next phases can extend functionality with Payment, Delivery, Notification, and CI/CD.
