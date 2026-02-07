# TimeTally

TimeTally is a personal time-tracking application backend built with Spring Boot.
It allows users to define tasks under predefined categories (sports, study, music, etc.), log time against those tasks until completion,
and generate statistics, reports, timesheets, and detailed logs.
The system also supports goal-based alerts when users reach or drift away from predefined time-spend targets.

This project is intentionally designed as a Spring Boot monolith with a clean-ish architecture, optimized for productivity, performance, and reduced boilerplate.

---

## Features

- Personal time tracking per user
- Task management under predefined categories
- Time logging until task completion
- Timesheets and detailed activity logs
- Category-based statistics and reports
- Goal tracking with alerts when:
    - A time-spend goal is reached
    - A user falls behind a target

---

## Architecture

- Spring Boot monolith
- Clean Architecture (clean-ish)
    - Separation of domain, application, and interface concerns
    - JPA repositories live in the application layer instead of infrastructure

This is a deliberate trade-off:
- Full leverage of Spring Data JPA
- Reduced boilerplate
- Avoidance of performance-impacting mapping layers

The design allows future evolution toward richer domain behavior such as events, async processing, and notifications.

---

## Tech Stack

- Java 21
- Spring Boot 3.5.9
- PostgreSQL 16
- Spring Data JPA
- Docker & Docker Compose
- JUnit, Testcontainers (planned / partially used)

---

## Getting Started

### Prerequisites

- Docker
- Docker Compose
- Java 21 (for local development)

### Running the Application

A docker-compose.yml file is provided for local startup.

Run:

docker-compose up

Once started:

- Application: http://localhost:8081
- PostgreSQL: localhost:5432
- PostgreSQL data volume: postgres_data

---

## REST API Overview

### User Management

| Method | Endpoint        | Description            |
|--------|-----------------|------------------------|
| POST   | /users          | Create a new user      |
| GET    | /users/{id}     | Get user by ID         |
| PUT    | /users/{id}     | Update user by ID      |
| DELETE | /users/{id}     | Delete user by ID      |


### Task Management

| Method | Endpoint                                     | Description                         |
|--------|----------------------------------------------|-------------------------------------|
| POST   | /users/{user-id}/tasks                       | Create a task for a user            |
| GET    | /users/{user-id}/tasks                       | List all tasks for a user           |
| PUT    | /users/{user-id}/tasks/{task-id}             | Update a task                       |
| PATCH  | /users/{user-id}/tasks/{task-id}/status      | Update task status                  |
| DELETE | /users/{user-id}/tasks/{task-id}             | Delete a task                       |

### Time Logs

| Method | Endpoint                                     | Description                         |
|--------|----------------------------------------------|-------------------------------------|
| POST   | /tasks/{task-id}/logs                        | Create a time log for a task        |
| GET    | /tasks/{task-id}/logs                        | List all logs for a task            |
| PUT    | /tasks/{task-id}/logs/{log-id}               | Update a time log                   |
| DELETE | /tasks/{task-id}/logs/{log-id}               | Delete a time log                   |



## Testing Strategy

The project emphasizes fast feedback while still validating real integrations.

### Test Types

- Unit Tests
    - Fast
    - Focused on business logic
- Integration Tests
    - Spring context with real components
- End-to-End Integration Tests
    - No MockMvc
    - Full HTTP and persistence stack

### Principles

- Fast tests should significantly outnumber slow tests
- Tests run against a real PostgreSQL database
- Database is cleaned after each test
- Testcontainers are planned to be used to:
    - Ensure production-like behavior
    - Provide isolated and repeatable environments

---

## Known Gaps and Planned Improvements

The following are necessary but not yet implemented due to time constraints:

- Exception Handling
    - Domain-specific exceptions
    - Uniform error response DTO
    - Standardized error codes and HTTP statuses (especially for 4xx)
- Security
    - JWT-based authentication
    - Pre-entered users
    - Hashed passwords stored in the database
- Domain Events
    - Aggregates publishing events (e.g. login, task completion)
- Async and Scheduling
    - Periodic or event-triggered report generation
- Infrastructure Layer
    - Email delivery
    - Notifications for reports, goals, and domain events

---

## Future Direction

TimeTally is designed to evolve into a richer personal analytics system with:

- Event-driven workflows
- Automated insights
- Notification and reporting pipelines
- Stronger domain boundaries without sacrificing performance

---

## License

This project is currently unlicensed and intended for personal or educational use.
