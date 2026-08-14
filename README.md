# 📝 Journal App — Enterprise Spring Boot REST API

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.14-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-47A248.svg)](https://www.mongodb.com/)
[![Redis](https://img.shields.io/badge/Redis-Cache-DC382D.svg)](https://redis.io/)
[![Kafka](https://img.shields.io/badge/Apache%20Kafka-Messaging-231F20.svg)](https://kafka.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A robust backend service built with **Spring Boot 3**, featuring **JWT-based Security**, **MongoDB persistence**, **Redis caching**, **Kafka event-driven sentiment processing**, and **automated Email notifications**.

---

## 🌟 Key Features

* 🔐 **Authentication & Authorization**: Stateless JWT (JSON Web Token) authentication with Spring Security and BCrypt password hashing.
* 📔 **Journal Entry CRUD**: User-scoped CRUD operations for daily journal entries with ownership verification.
* 🧠 **Sentiment Analysis Engine**: Automated weekly sentiment tracking across journal entries with asynchronous event messaging via **Apache Kafka**.
* 📧 **Email Notification Service**: Scheduled sentiment analysis digests emailed directly to users via SMTP.
* ⚡ **Redis Caching**: High-performance caching mechanism for fast app metadata retrieval and dynamic cache clearing.
* 🌤️ **External Weather API Integration**: Dynamic weather report fetching integrated into personalized user greetings.
* 🛡️ **Role-Based Access Control (RBAC)**: Enforced separation between public user endpoints and administrative controls (`ROLE_ADMIN`).

---

## 🛠️ Tech Stack & Architecture

* **Core Framework**: Java 17, Spring Boot 3.5.14
* **Security**: Spring Security, JWT (`jjwt` 0.12.6), BCrypt
* **Database**: MongoDB (Spring Data MongoDB)
* **Caching**: Redis (Spring Data Redis)
* **Messaging & Async Queues**: Apache Kafka (Spring Kafka)
* **Mail Service**: Spring Boot Mail (JavaMailSender / SMTP)
* **Utilities**: Lombok, SLF4J Logging, SonarQube Maven Scanner

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed on your machine:

* **JDK 17** or higher
* **Maven 3.8+**
* **MongoDB** (Local instance or MongoDB Atlas)
* **Redis Server** (Local or Cloud Redis instance)
* **Apache Kafka** (Running on `localhost:9092`)

---

## ⚙️ Configuration Setup

Configure your application properties in `src/main/resources/application-dev.yml` or set environment variables:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@cluster0.mongodb.net/?appName=Cluster0
      database: journaldb
      auto-index-creation: true
    redis:
      host: <redis-host>
      port: <redis-port>
      username: <redis-username>
      password: <redis-password>
  mail:
    host: smtp.gmail.com
    port: 587
    username: <your-email@gmail.com>
    password: <your-app-password>
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
    consumer:
      group-id: weekly-sentiment-group

server:
  port: 8080
  servlet:
    context-path: /journal

weather:
  api:
    key: <your-weather-api-key>
```

---

## 🏃 Running the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/journalApp.git
   cd journalApp
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Start the Application**
   ```bash
   mvn spring-boot:run
   ```

The server will start at `http://localhost:8080/journal`.

---

## 📡 API Reference

### 1. 🌐 Public Endpoints (`/public`)

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/public/health-check` | System health check | ❌ No |
| `POST` | `/public/signup` | Register a new user | ❌ No |
| `POST` | `/public/login` | Authenticate user & receive JWT token | ❌ No |

### 2. 👤 User Management (`/user`)

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/user` | Get personalized greeting with live weather info | 🔑 JWT |
| `PUT` | `/user` | Update user credentials | 🔑 JWT |
| `DELETE` | `/user` | Delete user account | 🔑 JWT |

### 3. 📖 Journal Entries (`/journal`)

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/journal` | Fetch all journal entries for logged-in user | 🔑 JWT |
| `POST` | `/journal` | Create a new journal entry | 🔑 JWT |
| `GET` | `/journal/id/{id}` | Fetch a specific journal entry by ID | 🔑 JWT |
| `PUT` | `/journal/id/{id}` | Update a journal entry by ID | 🔑 JWT |
| `DELETE` | `/journal/id/{id}` | Delete a journal entry by ID | 🔑 JWT |

### 4. 🔑 Admin Operations (`/admin`)

| Method | Endpoint | Description | Role Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/admin/all-users` | Retrieve list of all registered users | 🛡️ `ROLE_ADMIN` |
| `POST` | `/admin/create-admin-user` | Create a new admin account | 🛡️ `ROLE_ADMIN` |
| `GET` | `/admin/clean-app-cache` | Flush and re-initialize app cache | 🛡️ `ROLE_ADMIN` |

---

## 🧪 Testing

Run automated JUnit unit and integration tests:

```bash
mvn test
```

Includes test suites for:
* `UserServiceTest` & `UserDetailServiceImpTests`
* `UserSchedularTest`
* `RedisTest` & `EmailServiceTest`
* `UserRepositoryImpTest`

---

## 📝 License

Distributed under the MIT License. See `LICENSE` for details.
