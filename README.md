# Expense Tracker

## Overview
A Spring Boot REST application for tracking expenses. The project uses Spring Data JPA for persistence, Jackson for JSON serialization, and Lombok to reduce boilerplate. The `Expense` model (example: `src/main/java/com/stephenellis/expensetracker/model/Expense.java`) contains relationships to `Category` and `User` entities and is persisted via JPA.

## Note
You will need to create and connected your own database and then in the application.yaml file you will need to add your username and password for the database.
This is my first project building an application from frontend (using Postman for UI) to backend. The project was to learn about CRUD operations, learn new a new framework, Spring Boot, and apply what I have been learning 
in my Udemy PostgresSQL and SQL course. Along to further my strength in Java while adding AI (Chatgpt, and GitHub Copilot) into the workflow. Ai assistance was to refactor, help understand errors during the development, 
explain design and architecture principles. I achknowledge this project is not entirely design as efficient or as well as it could be. Again, this is a starting project to see where my mistakes are, how I could include 
Ai assisting tools to my work flow and refactoring, and new tools combine to create a basic CRUD application.

As I learn, improve, challenge myself and grow I will revist and possibly redesign this project. 

## Tools, Libraries, Packages & Frameworks
- Java (JDK 17+ recommended)
- Spring Boot (Spring Web, Spring Data JPA)
- Maven (build and dependency management)
- Jakarta Persistence API (jakarta.persistence)
- Hibernate (JPA implementation)
- Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`) — requires the Lombok plugin in IntelliJ
- Database: configured via application properties (commonly H2 for local/dev or PostgreSQL/MySQL for production)
- IDE: IntelliJ IDEA 2025.2.4 (project tested in this environment)
- Testing / API client: Postman (or curl)

## Key Maven dependencies (examples)
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-test (for tests)
- lombok
- com.fasterxml.jackson.core / jackson-databind
- database driver (H2 / postgresql / mysql)

(See `pom.xml` for exact dependency versions.)

## Application behavior
- Exposes REST endpoints for managing expenses (CRUD).
- `Expense` includes: `id`, `category` (relation), `description`, `amount` (BigDecimal), `date` (LocalDate), and `user` (relation).
- Uses JPA/Hibernate to persist entities to a relational database configured in `src/main/resources/application.properties` (or `application.yml`).
- JSON serialization ignores Hibernate lazy initializer properties via `@JsonIgnoreProperties` on entity relationships.

## Requirements
- JDK 17 or newer installed
- Maven installed
- Lombok plugin enabled in IntelliJ (and annotation processing enabled)
- Database configured (H2 for embedded/dev or external DB with connection properties)

## Pull & Build
1. Pull the repo:
   - `git clone <repository-url>`
   - `cd <repository-directory>`

2. Build with Maven:
   - `mvn clean package`

3. Run with Maven:
   - `mvn spring-boot:run`

   Or run the packaged JAR:
   - `java -jar target/<artifact>-<version>.jar`

4. Run from IntelliJ:
   - Open the project (`File` -> `Open` -> select project)
   - Ensure Lombok plugin is installed and annotation processing is enabled
   - Run the application via the `Application` main class (Spring Boot run configuration)

## Configuration
- Edit database and server settings in:
  - `src/main/resources/application.properties`
  - or `src/main/resources/application.yml`

- Common properties:
  - `spring.datasource.url`
  - `spring.datasource.username`
  - `spring.datasource.password`
  - `spring.jpa.hibernate.ddl-auto`
  - `server.port` (default 8080)

## Testing with Postman
1. Start the application (see Run steps). Default: `http://localhost:8080`.

2. Common endpoints (adjust paths to match your controller mappings; typical examples):
   - GET all expenses: `GET http://localhost:8080/api/expenses`
   - GET expense by id: `GET http://localhost:8080/api/expenses/{id}`
   - Create expense: `POST http://localhost:8080/api/expenses`
   - Update expense: `PUT http://localhost:8080/api/expenses/{id}`
   - Delete expense: `DELETE http://localhost:8080/api/expenses/{id}`

3. Request headers:
   - `Content-Type: application/json`
   - If authentication is enabled, add `Authorization` header with appropriate token or credentials.

4. Sample JSON for creating an expense (adjust `category` and `user` to match your API shape—IDs or nested objects as implemented):
```json
{
  "category": { "id": 1 },
  "description": "Coffee",
  "amount": 3.50,
  "date": "2025-11-24",
  "user": { "id": 1 }
}
