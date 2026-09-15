# PetStore API – Solución Entrevista Técnica

Una **API REST con Spring Boot** que se integra con la [Swagger PetStore API](https://petstore.swagger.io/v2) pública para demostrar arquitectura cliente-servidor, capas de servicio y buenas prácticas REST.

> 📄 Documento completo de la solución: [Technical Interview Solution.pdf](https://github.com/leoramzmusic/PetStoreAPITechnicalInterview/blob/main/Technical%20Interview%20Solution.pdf)

---

## 📋 Descripción General

Este proyecto fue desarrollado como parte de un ejercicio de entrevista técnica. Expone dos endpoints REST que funcionan como **proxy/adaptador** sobre la API externa de PetStore:

| Método | Endpoint            | Descripción                                             |
|--------|---------------------|---------------------------------------------------------|
| GET    | `/api/pet/{petId}`  | Obtiene una mascota por ID desde la API externa         |
| POST   | `/api/pet`          | Crea una nueva mascota y devuelve metadata enriquecida  |

---

## 🏗️ Arquitectura

El proyecto sigue una **arquitectura de 3 capas** limpia:

```
Controlador  →  Servicio  →  Cliente (API Externa)
```

```
src/main/java/com/example/leo/demo/
├── DemoApplication.java              # Punto de entrada de Spring Boot
└── exam/
    ├── controller/
    │   └── PetController.java        # Endpoints REST
    ├── service/
    │   └── PetService.java           # Lógica de negocio
    ├── client/
    │   └── PetClient.java            # Cliente HTTP hacia la API de PetStore
    └── model/
        ├── Pet.java                  # Modelo de entidad Pet
        └── PetResponse.java          # Wrapper de respuesta con metadata
```

### Responsabilidades por Capa

- **`PetController`** — Recibe las peticiones HTTP y delega al servicio.
- **`PetService`** — Contiene la lógica de negocio; orquesta las llamadas al cliente externo.
- **`PetClient`** — Usa `RestTemplate` para comunicarse con `https://petstore.swagger.io/v2/pet`.
- **`Pet`** — Modelo Java con campos `id`, `name` y `status`.
- **`PetResponse`** — Respuesta enriquecida que agrega `transactionId` (UUID) y `dateCreated` (timestamp) a cada resultado del POST.

---

## 🚀 Cómo Ejecutar

### Requisitos Previos

- **Java 17+**
- **Gradle** (wrapper incluido)
- Acceso a internet (para conectarse a la API externa de PetStore)

### Ejecución Local

```bash
# Clonar el repositorio
git clone https://github.com/leoramzmusic/PetStoreAPITechnicalInterview.git
cd PetStoreAPITechnicalInterview

# Compilar y ejecutar
./gradlew bootRun
```

El servidor inicia en **`http://localhost:8080`** por defecto.

---

## 📡 Endpoints de la API

### GET `/api/pet/{petId}`

Consulta una mascota desde la API externa de Swagger PetStore por su ID numérico.

**Petición:**
```
GET http://localhost:8080/api/pet/1
```

**Respuesta (200 OK):**
```json
{
  "id": 1,
  "name": "doggie",
  "status": "available"
}
```

> ⚠️ Si la mascota no es encontrada en la API externa, se devuelve un objeto de respaldo con `name: "Pet not found"` y `status: "unknown"`.

---

### POST `/api/pet`

Crea una nueva mascota enviando la petición a la API externa y devuelve metadata enriquecida.

**Petición:**
```
POST http://localhost:8080/api/pet
Content-Type: application/json

{
  "id": 99,
  "name": "Firulais",
  "status": "available"
}
```

**Respuesta (200 OK):**
```json
{
  "transactionId": "550e8400-e29b-41d4-a716-446655440000",
  "dateCreated": "2026-09-15T10:30:00",
  "name": "Firulais",
  "status": "available"
}
```

> ⚠️ Si la llamada a la API externa falla, el campo `status` se devuelve como `"failed"`.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología         | Versión  | Propósito                            |
|--------------------|----------|--------------------------------------|
| Java               | 17       | Lenguaje principal                   |
| Spring Boot        | 3.2.7    | Framework de aplicación              |
| Spring Web         | -        | Controladores REST y RestTemplate    |
| Gradle             | Wrapper  | Herramienta de construcción          |
| JUnit 5            | -        | Pruebas unitarias                    |

---

## 🔌 API Externa

Este proyecto se integra con la **Swagger PetStore API** pública:

- **URL Base:** `https://petstore.swagger.io/v2/pet`
- **Obtener por ID:** `GET /pet/{petId}`
- **Crear mascota:** `POST /pet`
- **Documentación:** [https://petstore.swagger.io](https://petstore.swagger.io)

---

## 📂 Estructura del Proyecto

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

## 📄 Licencia

Este proyecto fue creado con fines de entrevista técnica. Puedes usarlo libremente como referencia.
