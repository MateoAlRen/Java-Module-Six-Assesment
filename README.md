# Credit Application System (Hexagonal Architecture)

## Description

This project implements a **credit application management system** with a **hexagonal architecture**, JWT-based security, and an external mock credit risk microservice. It follows enterprise standards for architecture, security, transactions, testing, and deployment.

The system consists of:

1. **credit-application-service**: Central credit application service, featuring:
   - **Affiliate management** (register, edit, validations).
   - **Credit application management** (full workflow including risk evaluation and automatic decision).
   - **JWT Security** with roles: `ROLE_AFFILIATE`, `ROLE_ANALYST`, `ROLE_ADMIN`.
   - **Persistence** with JPA/Hibernate.
   - **Hexagonal architecture** with in/out ports and independent use cases.
   - **Metrics and observability** via Spring Actuator and optional Micrometer.

2. **risk-central-mock-service**: Lightweight external mock microservice for credit risk, featuring:
   - Accepts document, amount, and term.
   - Generates a deterministic score and risk level per document.
   - Returns consistent results for the same document, different for others.
   - No persistence or security.

---

## Technologies

- **Java 21**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **Spring Security + JWT**
- **Spring Web / REST**
- **H2 / PostgreSQL** (Database)
- **Micrometer + Actuator**
- **Maven**
- **JUnit + Mockito** (Unit Testing)
- **Docker / Docker Compose** (Deployment)

---

## Architecture

**Hexagonal / Ports & Adapters**

- **Domain**: Pure business models and enums (`Affiliate`, `Request`, `Risk`, `Status`, etc..).
- **Ports (In/Out)**: Interfaces defining use cases (In) and repositories / external clients (Out).
- **Application Services**: Implements domain use cases using ports.
- **Infrastructure Adapters**:
  - **JPA**: Maps domain models to database entities and implements repository ports.
  - **REST client**: Communicates with risk-central-mock-service.
  - **Controllers**: REST endpoints exposing use cases.
- **Security**: JWT authentication, password encryption, role-based access.

---

## Domain Models

- `Affiliate`: Represents an affiliate, with document, name, salary, joining date, and status.
- `Request`: Represents a credit application, with affiliate reference, amount, term, proposed rate, request date, status, and risk evaluation.
- `RiskEvaluation`: Contains risk score, level, detail, and decision reason.
- Enums:
  - `EstadoAfiliado`: `ACTIVE`, `INACTIVE`.
  - `EstadoSolicitud`: `PENDING`, `APROBBED`, `REJECTED`.
  - `NivelRiesgo`: `HIGH`, `MEDIUM`, `LOW`.
---

## Use Cases

1. **Register Affiliate**: Validates unique document, salary > 0, sets default joining date, saves affiliate.
2. **Update Affiliate**: Allows updating name, salary, status; validates rules.
3. **Create Credit Application**:
   - Validates affiliate active and minimum 6 months membership.
   - Persists initial request as `PENDING`.
   - Calls `risk-central-mock-service` to get risk evaluation.
   - Applies internal rules (quota/income ratio, max amount, score level).
   - Updates application status: `APPROBED` or `REJECTED`.
4. **Evaluate Credit Application**: (Optional) separate evaluation endpoint if needed.

---


## ENDPOINTS

```
POST evaluate : http://localhost:8080/api/requests/{id}/evaluate
POST request : http://localhost:8080/api/requests
POST affiliate : http://localhost:8080/api/afiliated
POST register: http://localhost:8080/auth/register
POST login : http://localhost:8080/auth/login
```


By: Mateo Algarin Rendon

email: mateoalgarinrendon38@gmail.com

C.C. : 1034.919.321
