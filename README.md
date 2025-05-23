# Freelance Marketplace API

A backend RESTful API for a freelance job marketplace where users can create jobs, assign freelancers, and exchange comments and evaluations. Built with **Spring Boot**, **PostgreSQL**, and **RabbitMQ**. Developed as part of a software architecture project.

---

## Project Links

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html#/
---

## Technologies Used

- Java 17
- Spring Boot 3.x / 3.4.5
- Spring Data JPA (Hibernate)
- PostgreSQL 16
- RabbitMQ
- Maven
- SpringDoc (Swagger/OpenAPI) (Latest 2.8.6)
- Docker + Docker Compose
- Dotenv for environment management

---
## GitlabFlow

![GitLabFlow](https://github.com/user-attachments/assets/a551a3bd-d32f-4608-8abf-74ecda030ca6)

![createFreelancerFlow](https://github.com/user-attachments/assets/8f820ba5-27db-45af-b41a-675a55be53da)


## API Summary

| Endpoint                              | Method | Auth | Description                                     |
|---------------------------------------|--------|------|-------------------------------------------------|
| `/api/v1/freelancers`                | POST   | ❌   | Create a new freelancer                         |
| `/api/v1/freelancers`                | GET    | ❌   | Get all freelancers                             |
| `/api/v1/freelancers/{id}`           | GET    | ❌   | Get freelancer by ID                            |
| `/api/v1/freelancers/search`         | GET    | ❌   | Search freelancers by name, city, type, etc.    |
| `/api/v1/jobs`                       | POST   | ❌   | Create a new job                                |
| `/api/v1/jobs/{id}`                  | PUT    | ❌   | Update an existing job                          |
| `/api/v1/jobs/freelancer/{freelancerId}`| GET    | ❌   | Get all jobs (by freelancerId)                  |
| `/api/v1/jobs/{id}`                  | GET    | ❌   | Get job by ID                                   |
| `/api/v1/comments`                   | POST   | ❌   | Add a comment to a job                          |
| `/api/v1/comments/{id}`              | PUT    | ❌   | Update a comment                                |
| `/api/v1/comments/job/{jobId}`       | GET    | ❌   | Get comments for a specific job                 |

---

## RabbitMQ Integration

- **Exchange:** `freelancer-exchange`
- **Queue:** `freelancer-evaluation-queue`
- **Routing Key:** `freelancer-evaluation-routing-key`
- Async messaging support to handle freelancer evaluations and events

---

## Design Decisions

- Uses **DTOs** for clean separation between data layer and API layer
- RabbitMQ connection managed via `spring.rabbitmq.*` properties
-  DTO-based architecture for clean request/response separation
- Manual 404 handling using `ResponseEntity.notFound().build()` (no `@ControllerAdvice`)
- Used `@Valid` and constraint annotations (`@NotBlank`, `@NotNull`) for field-level validation
- All search filters are optional and handled via query parameters
- Container-based development for RabbitMQ and PostgreSQL
- No authentication implemented (public endpoints only)

---

## Assumptions

- Freelancers are either SOFTWARE_DEVELOPER or DESIGNER
  
- Job status either IN_PROGRESS or FINISHED

- DESIGNER has portfolioUrl, designTools

- SOFTWARE_DEVELOPER has softwareLanguages, specialties

- Each freelancer can have multiple jobs

- Each job can have multiple comments

- Comments are associated with jobs and include createdDate

- Evaluation scores are simulated via RabbitMQ and saved with freelancer

## Sample Scenarios

Create a freelancer → Create a job → Add comment → Update job

Search for freelancers by city, type, specialty

Evaluation message published on freelancer creation

Try to update a non-existent job → return 404

Leave optional search parameters empty → return full or filtered list

Test @Valid rejection with missing required fields (name, status)

## Entity Overview
- Freelancer
- Job
- Comment

## Environment Variables
```text
This project uses a .env file to manage environment-specific configurations such as database credentials and RabbitMQ settings.

Example .env file:

env
Copy
Edit
DB_URL=jdbc:postgresql://postgres:5432/freelance_marketplace
DB_USERNAME=postgres
DB_PASSWORD=12345678
DB_PORT=5432
DB_NAME=freelance_marketplace
DB_HOST=postgres

RABBITMQ_HOST=rabbitmq
RABBITMQ_PORT=5672
RABBITMQ_USERNAME=guest
RABBITMQ_PASSWORD=guest
RABBITMQ_EXCHANGE=freelancer-exchange
RABBITMQ_QUEUE=freelancer-evaluation-queue
RABBITMQ_ROUTING_KEY=freelancer-evaluation-routing-key
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
SPRING_JPA_SHOW_SQL=true
spring.docker.compose.readiness.wait=NEVER

SERVER_PORT=8080
Note: Make sure to create this file in your project root. It is already excluded from Git using .gitignore.
```

## How to Run Locally
```text
1. Clone the project
git clone https://github.com/Gizoskos/freelance-marketplace-api.git
cd freelance-marketplace-api

2. Start services (RabbitMQ & PostgreSQL)
docker-compose up --build
docker-compose up -d

3. Configure application properties
Update src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/freelancedb
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

4. Build and run the project
mvn clean install
mvn spring-boot:run

5. Open Swagger UI
http://localhost:8080/swagger-ui/index.html
```


## Problems & Solutions
```text
| Problem                                                                 | Solution                                                                                          |
|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| `GlobalExceptionHandler` broke Swagger UI rendering                    | Removed `@ControllerAdvice`, replaced with `ResponseEntity.notFound().build()` in controllers    |
| Validation errors returned 500 instead of 400                          | Added `@Valid` to controller methods and used validation annotations like `@NotNull`, `@NotBlank` |
| Empty query params returned full list in search                        | Added `!param.isBlank()` checks to filter only non-empty query parameters                        |
| Docker Compose: container name conflict (`freelance-postgres` exists)  | Resolved by removing existing container with `docker rm freelance-postgres`                      |
| Postman sent incorrect payload for GET `/search`                       | Used query params instead of raw JSON (GET requests do not support body payloads)                |
| RabbitMQ message was not being consumed                                | Verified queue bindings and enabled correct exchange-routingKey configuration                    |
| Test expected exception but controller returned 404 manually           | Removed `assertTrue(exception...)`, tested only status with `.andExpect(status().isNotFound())`  |
| Swagger didn’t show enums in dropdown                                  | Ensured DTO fields use `FreelancerType` enum directly and not as String                          |
| Unit test failed with `MethodArgumentNotValidException`                | Added proper values for all `@NotNull` fields in the request body                                |
| Swagger showed `Unable to render definition` error                     | Replaced `springdoc-openapi-ui` with `springdoc-openapi-starter-webmvc-ui` for Spring Boot 3.x   |
```

## Project Structure

```text
com.gizem.freelancemarketplaceapi
├── controller       → REST endpoints
├── dto              → Data Transfer Objects
├── entity           → JPA entity models
├── repository       → Spring Data interfaces
├── service          → Business logic
├── config           → RabbitMQ + Swagger config
├── exception        → Custom exceptions (Not usable anymore)
```
```text
Author: Gizem Gültoprak
```
