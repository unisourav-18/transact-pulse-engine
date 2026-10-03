# TransactPulse | Enterprise Ingestion & Idempotency Engine

[![Java 17](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3+-green.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)](https://www.docker.com/)
[![Live Swagger Docs](https://img.shields.io/badge/API%20Docs-Live%20Swagger%20UI-brightgreen.svg)](https://transact-pulse-engine.onrender.com/swagger-ui.html)

A high-concurrency transaction validation and ingestion microservice engineered in Java and Spring Boot. Enforces strict API contracts, deterministic request idempotency, and transactional persistence to eliminate race conditions and duplicate debits in distributed event streams.

---

## 🚀 Live Demo & Documentation
* **Interactive Swagger UI:** [transact-pulse-engine.onrender.com/swagger-ui.html](https://transact-pulse-engine.onrender.com/swagger-ui.html)
* **Health Endpoint:** [transact-pulse-engine.onrender.com/api/v1/transactions/health](https://transact-pulse-engine.onrender.com/api/v1/transactions/health)

---

## 🛠️ Architecture & Core Primitives
* **Deterministic Idempotency:** Implements indexed uniqueness checks on `transactionId`, returning an immediate `409 CONFLICT` on duplicates to safeguard ledger integrity.
* **Payload Validation:** Enforces JSR-380 constraints (`@DecimalMin`, regex account routing, ISO-4217 currency checks) at the ingress controller layer.
* **ACID Persistence:** Spring Data JPA with HikariCP connection pooling, providing indexed lookup speeds on transaction and account entities.
* **Containerized Deployment:** Multi-stage Dockerized build deployed to Render cloud infrastructure.

---

## 📡 API Specification

### `POST /api/v1/transactions/ingest`
**Request Payload:**
```json
{
  "transactionId": "TXN-9021482-AMX",
  "accountId": "987654321012",
  "amount": 249.50,
  "currency": "USD",
  "merchantCategoryCode": "5411"
}