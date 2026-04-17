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
