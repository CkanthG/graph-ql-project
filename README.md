# GraphQL Project

A simple Spring Boot application that demonstrates a GraphQL API for retrieving books and their authors.

## Overview

This project exposes a GraphQL endpoint with a small in-memory dataset of books and authors. It demonstrates:

- Spring Boot application setup
- Spring for GraphQL annotations
- GraphQL schema definition
- nested object resolving via `@SchemaMapping`
- GraphiQL UI for interactive API testing

## Tech Stack

- Java 21
- Spring Boot 4.1.0
- Spring WebMVC
- Spring GraphQL
- Gradle
- JUnit 5
- GraphQL test support (`spring-boot-starter-graphql-test`)

## Project Structure

```text
graph-ql-project/
├── src/
│   ├── main/
│   │   ├── java/com/sree/graphql/
│   │   │   ├── controller/
│   │   │   │   └── BookController.java
│   │   │   ├── models/
│   │   │   │   ├── Author.java
│   │   │   │   └── Book.java
│   │   │   └── GraphQlProjectApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── graphql/
│   │           └── schema.graphqls
│   └── test/java/com/sree/graphql/
│       └── GraphQlProjectApplicationTests.java
├── build.gradle
├── gradlew
├── gradlew.bat
├── settings.gradle
└── README.md
```

## Prerequisites

Before running the project, make sure you have:

- JDK 21 installed
- Gradle wrapper available (or Gradle installed separately)
- Internet access to download project dependencies from Maven Central

## Run the Application

From the project root, run:

```bash
./gradlew bootRun
```

On Windows, you can use:

```powershell
./gradlew.bat bootRun
```

The application starts on:

- HTTP server: http://localhost:8082
- GraphQL endpoint: http://localhost:8082/graphql
- GraphiQL UI: http://localhost:8082/graphiql

## GraphQL Schema

The project exposes the following root queries:

```graphql
type Query {
  bookById(id: ID): Book
  bookByName(name: String): [Book]!
}

type Book {
  id: ID
  name: String
  pageCount: Int
  author: Author
}

type Author {
  id: ID
  firstName: String
  lastName: String
}
```

## Example Query

```graphql
query {
  bookById(id: "book-1") {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
```

## Running Tests

```bash
./gradlew test
```

This runs the included GraphQL integration tests that validate book lookup and nested author data.

## Notes

- The data is stored in memory, not in a database.
- This project is intended as a minimal GraphQL sample and learning project.
- The GraphQL schema is defined in `src/main/resources/graphql/schema.graphqls` and matched to Java methods using Spring GraphQL annotations.
