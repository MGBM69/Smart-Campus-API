# Smart-Campus-API

## Smart Campus Sensor & Room Management API

**Module**: 5COSC022W Client-Server Architectures
**Author**: Banuka Malshan
**GitHub**: [Repository](https://github.com/MGBM69/Smart-Campus-API)

## Overview

### Key Features

## Technology Stack

- Java 17
- JAX-RS (Jersy 2.32)
- Jackson (JSON)
- Maven

## How to Build and Run

### Prerequisites

- Java 17 installed
- Maven installed
- Git installed

### Step 1 - Clone the repository

```
git clone https://github.com/MGBM69/Smart-Campus-API.git

```

### Step 2 - Build the Project

```
mvn clean install

```

### Step 3 - Run the server

```
java -jar target/Smart-Campus-API-1.0-SNAPSHOT.jar

```

### Step 4 - Access the API

The server will start on port 8080.

```
http://localhost:8080/Smart-Campus-API/api/v1

```

# Report - Question Answers

## Part 1.1 - JAX-RS Resource Lifecycle

In a JAX-RS application, resource classes follow a per-request lifecycle by default, meaning that a new instance of the resource class is created for each incoming HTTP request. This ensures that each request is handled independently and prevents unintended sharing of state between requests. As a result, resource classes are typically designed to be stateless, which aligns with the core principles of RESTful architecture.

Although resource instances are created per request, shared data structures such as in-memory lists or maps (e.g., room and sensor collections) are usually defined as static or application-scoped objects. These shared resources are accessed by multiple requests concurrently, which introduces the risk of race conditions and data inconsistency if multiple threads attempt to modify them simultaneously.

Therefore, developers must implement proper synchronization mechanisms to ensure thread safety when managing shared data. This can include using thread-safe collections (e.g., synchronized lists) or applying synchronization techniques such as synchronized blocks. Without these precautions, concurrent operations may lead to data corruption or unexpected behavior, even though the resource classes themselves are instantiated per request.

## Part 1.2 - HATEOAS

Hypermedia as the Engine of Application State (HATEOAS) is considered a hallmark of advanced RESTful design because it allows clients to dynamically navigate an API through links provided in server responses rather than relying solely on static documentation. By embedding relevant URLs (e.g., links to related resources like rooms or sensors) within responses, the server guides the client on what actions are available next, making the API more self-descriptive and adaptable. This approach benefits client developers by reducing tight coupling to hardcoded endpoints, improving flexibility when APIs evolve, and enabling easier integration since clients can discover and interact with resources at runtime without needing constant updates to external documentation.

## Part 2.1 Full Room Object vs Only ID s

When returning a list of rooms, choosing between returning only IDs or full room objects has trade-offs related to performance and usability. Returning only IDs reduces the size of the response payload, which improves network efficiency and is beneficial when dealing with large datasets. However, it requires the client to make additional requests to retrieve full details, increasing client-side complexity and the number of API calls.

On the other hand, returning full room objects (as implemented in this system) provides all necessary information in a single response, making it easier for client applications to process and display data without additional requests. Although this increases the response size slightly, it improves usability and reduces round-trip communication. Therefore, returning full objects is more suitable for smaller datasets and improves overall developer experience.

## 2.2 - Is DELETE Idempotent

The DELETE operation in this implementation is **idempotent**, meaning that performing the same DELETE request multiple times results in the same outcome. When a room is deleted successfully, it is removed from the system. If the same DELETE request is sent again for the same room, the system will no longer find the resource and will return a **404 Not Found** response.

This behavior ensures that repeated DELETE requests do not cause unintended side effects or additional changes to the system state. The first request removes the resource, and subsequent requests simply confirm that the resource no longer exists. Therefore, the DELETE operation maintains idempotency while also enforcing business rules, such as preventing deletion when active sensors are present

## 3.1 - Usage of ` @Consumes(MediaType.APPLICATION_JSON)`

In the implemented **SensorResource** class, the `@Consumes(MediaType.APPLICATION_JSON)` annotation ensures that the POST method only accepts request bodies formatted as JSON. When a client sends data, JAX-RS uses a message body reader (such as Jackson) to automatically convert the incoming JSON into a Sensor Java object. This allows seamless integration between the client request and server-side processing, ensuring that the API can correctly interpret and validate the sensor data before storing it.

If a client attempts to send data in a different format, such as text/plain or application/xml, JAX-RS will not find a suitable message body reader to process the request. As a result, the framework automatically rejects the request and returns a **415 Unsupported Media Type** response. This built-in mechanism enforces strict content negotiation, ensuring that only supported data formats are processed and preventing potential data parsing errors or inconsistencies.

## 3.2 - QueryParams and PathParams Filtering

In the SensorResource class, filtering is implemented using `@QueryParam("type")`, which allows the same endpoint to handle both normal and filtered requests. When the query parameter is not provided, the condition `if(type == null || type.isEmpty())` is triggered, and the method returns the full list of sensors. This represents a standard GET request (/api/v1/sensors) where all resources are retrieved. When the query parameter is provided (e.g., /api/v1/sensors?type=Temparature), the method filters the list and returns only matching temparature sensors. This design makes the endpoint flexible by supporting both general and specific queries within a single method.

In contrast, a path-based approach such as /api/v1/sensors/type/Temparature would require a separate endpoint and treats the filter value as part of the resource path rather than a query condition. This reduces flexibility and makes it harder to support multiple filtering criteria. The query parameter approach is generally superior because it allows optional filtering, supports multiple parameters, and aligns with REST principles by treating filters as modifiers of a resource collection rather than defining entirely new resource paths.

## 4 - Architectural Benefits of the Sub-Resource Locator Pattern.

The Sub-Resource Locator pattern provides a structured way to handle nested resources by delegating responsibility to specialized classes. In this implementation, the **SensorResource** class handles the main `/sensors` collection, while requests to `/sensors/{sensorId}/readings` are delegated to the **SensorReadingResource** class through a sub-resource locator method. This approach clearly separates concerns, as sensor-related operations (such as creation and filtering) are handled in one class, while reading-related operations (such as fetching history and adding new readings) are managed in another. By passing the **sensorId** context to the **SensorReadingResource**, the system maintains a logical relationship between resources while keeping the code modular and easy to maintain.

In contrast, implementing all nested endpoints (e.g.,` /sensors/{id}/readings/{rid}`) within a single large controller class would significantly increase complexity and reduce code readability. A monolithic design would mix multiple responsibilities, making the code harder to debug, extend, and test. By delegating logic to separate classes, the API becomes more scalable and maintainable, allowing developers to independently modify or extend specific parts (such as adding new reading operations) without affecting unrelated functionality. This design aligns with best practices in RESTful architecture by promoting modularity, reusability, and clear resource hierarchy management.

## 5.1 - Why is HTTP 402 more Accurate than the 404 Status Code?

HTTP 422 (Unprocessable Entity) is considered more semantically accurate in this scenario because the client’s request is syntactically correct and well-formed, but contains invalid logical data. In the implemented **SensorResource** POST method, the client sends a valid JSON payload to create a new sensor, but the provided roomId does not exist in the system. This means the server understands the request structure, but cannot process it due to a violation of business rules. Therefore, returning 422 clearly communicates that the error lies in the request’s content rather than its format.

In contrast, a 404 (Not Found) response is typically used when the requested resource itself (e.g., a URL endpoint) does not exist. Using 404 in this case could be misleading, as the `/api/v1/sensors` endpoint is valid and reachable. The issue is not that the resource is missing, but that the relationship within the request is invalid. By using 422, the API provides a more precise and meaningful response, helping client developers quickly identify and correct logical errors in their request data.
