# PetStore API – Technical Interview Solution

A **Spring Boot** REST API that integrates with the public [Swagger PetStore API](https://petstore.swagger.io/v2) to demonstrate client-server architecture, service layering, and REST best practices.

> 📄 Full solution document: [Technical Interview Solution.pdf](https://github.com/leoramzmusic/PetStoreAPITechnicalInterview/blob/main/Technical%20Interview%20Solution.pdf)

---

## 📋 Overview

This project was built as part of a technical interview exercise. It exposes two REST endpoints that act as a **proxy/adapter** over the external PetStore API:

| Method | Endpoint            | Description                                   |
|--------|---------------------|-----------------------------------------------|
| GET    | `/api/pet/{petId}`  | Retrieve a pet by ID from the external API    |
| POST   | `/api/pet`          | Create a new pet and return enriched metadata |

---

## 🏗️ Architecture

The project follows a clean **3-layer architecture**:

```
Controller  →  Service  →  Client (External API)
```

```
src/main/java/com/example/leo/demo/
├── DemoApplication.java              # Spring Boot entry point
└── exam/
    ├── controller/
    │   └── PetController.java        # REST endpoints
    ├── service/
    │   └── PetService.java           # Business logic
    ├── client/
    │   └── PetClient.java            # HTTP client to PetStore API
    └── model/
        ├── Pet.java                  # Pet entity model
        └── PetResponse.java          # Response wrapper with metadata
```

### Layer Responsibilities

- **`PetController`** — Receives HTTP requests and delegates to the service layer.
- **`PetService`** — Contains business logic; orchestrates calls to the external API client.
- **`PetClient`** — Uses `RestTemplate` to communicate with `https://petstore.swagger.io/v2/pet`.
- **`Pet`** — Plain Java model with `id`, `name`, and `status` fields.
- **`PetResponse`** — Enriched response that adds `transactionId` (UUID) and `dateCreated` (timestamp) to every POST result.

---

## 🚀 Getting Started

### Prerequisites

- **Java 17+**
- **Gradle** (wrapper included)
- Internet access (to reach the external PetStore API)

### Run Locally

```bash
# Clone the repository
git clone https://github.com/leoramzmusic/PetStoreAPITechnicalInterview.git
cd PetStoreAPITechnicalInterview

# Build and run
./gradlew bootRun
```

The server starts on **`http://localhost:8080`** by default.

---

## 📡 API Endpoints

### GET `/api/pet/{petId}`

Fetches a pet from the external Swagger PetStore API by its numeric ID.

**Request:**
```
GET http://localhost:8080/api/pet/1
```

**Response (200 OK):**
```json
{
  "id": 1,
  "name": "doggie",
  "status": "available"
}
```

> ⚠️ If the pet is not found in the external API, a fallback object is returned with `name: "Pet not found"` and `status: "unknown"`.

---

### POST `/api/pet`

Creates a new pet by forwarding the request to the external API, then returns enriched metadata.

**Request:**
```
POST http://localhost:8080/api/pet
Content-Type: application/json

{
  "id": 99,
  "name": "Firulais",
  "status": "available"
}
```

**Response (200 OK):**
```json
{
  "transactionId": "550e8400-e29b-41d4-a716-446655440000",
  "dateCreated": "2026-09-15T10:30:00",
  "name": "Firulais",
  "status": "available"
}
```

> ⚠️ If the external API call fails, `status` is returned as `"failed"`.

---

## 🛠️ Tech Stack

| Technology         | Version  | Purpose                         |
|--------------------|----------|---------------------------------|
| Java               | 17       | Core language                   |
| Spring Boot        | 3.2.7    | Application framework           |
| Spring Web         | -        | REST controllers & RestTemplate |
| Gradle             | Wrapper  | Build tool                      |
| JUnit 5            | -        | Unit testing                    |

---

## 🔌 External API

This project integrates with the public **Swagger PetStore API**:

- **Base URL:** `https://petstore.swagger.io/v2/pet`
- **GET by ID:** `GET /pet/{petId}`
- **Create pet:** `POST /pet`
- **Docs:** [https://petstore.swagger.io](https://petstore.swagger.io)

---

## 📂 Project Structure

```
PetStoreAPITechnicalInterview/
├── src/
│   ├── main/
│   │   ├── java/com/example/leo/demo/
│   │   │   ├── DemoApplication.java
│   │   │   └── exam/
│   │   │       ├── client/PetClient.java
│   │   │       ├── controller/PetController.java
│   │   │       ├── model/Pet.java
│   │   │       ├── model/PetResponse.java
│   │   │       └── service/PetService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── build.gradle
├── gradlew / gradlew.bat
├── Technical Interview Solution.pdf
└── README.md
```

---

## 📄 License

This project was created for technical interview purposes. Feel free to use it as a reference.
