# Spring Boot RestClient Demo Application

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-restclient/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-restclient/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot)
![Dependabot](https://img.shields.io/badge/Dependabot-enabled-025E8C?logo=dependabot)

A comprehensive demonstration of Spring Boot 4.1.2 (Java 25) with RestClient functionality, showcasing various HTTP client
patterns, JPA integration, and REST API development.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Database](#database)
- [Testing](#testing)
- [RestClient Examples](#restclient-examples)
- [CI/CD & Dependency Management](#-cicd--dependency-management)
- [Contributing](#contributing)

## 🎯 Overview

This project demonstrates the usage of Spring Boot's RestClient (introduced in Spring Framework 6.1) along with
traditional web development patterns. It includes examples of:

- RestClient for making HTTP requests
- JPA entities and repositories
- REST API endpoints
- H2 in-memory database integration
- Exception handling patterns
- Multiple HTTP client configurations

## ✨ Features

- **RestClient Integration**: Modern Spring HTTP client examples
- **JPA & Hibernate**: Database operations with Spring Data JPA
- **H2 Database**: In-memory database with web console
- **REST APIs**: CRUD operations for User and Employee entities
- **External Service Integration**: JSONPlaceholder API integration
- **Exception Handling**: Custom exception handling for HTTP errors
- **Multiple Client Patterns**: Various RestClient usage patterns
- **WebFlux Support**: Reactive web stack integration

## 🛠 Technology Stack

- **Java**: 25 (LTS)
- **Spring Boot**: 4.1.2
- **Spring Framework**: 7.0.10
- **Spring Data**: 2026.0.2
- **Spring WebFlux**: 7.0.10
- **Hibernate ORM**: 7.4.13.Final
- **Jackson**: 3.1.7 (`tools.jackson.*` packages)
- **H2 Database**: 2.4.240 (runtime)
- **Apache HttpClient 5**: 5.6.5 / HttpCore 5.4.4
- **Lombok**: 1.18.48
- **JUnit Jupiter**: 6.0.3
- **Maven**: 3.10.0 (via Maven Wrapper)

## 📋 Prerequisites

- Java 25 or higher (e.g. [Eclipse Temurin 25](https://adoptium.net/))
- Maven is optional — the bundled Maven Wrapper (`./mvnw`) downloads Maven 3.10.0 automatically
- Git (for cloning the repository)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/hendisantika/spring-boot-restclient.git
cd spring-boot-restclient
```

### 2. Build the Project

```bash
./mvnw clean package
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

Or run the packaged jar:

```bash
java -jar target/spring-boot-restclient-0.0.1-SNAPSHOT.jar
```

### 4. Access the Application

- **Application**: http://localhost:8081
- **H2 Console**: http://localhost:8081/h2-console
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: `password`

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/hendisantika/springbootrestclient/
│   │   ├── client/              # RestClient service implementations
│   │   ├── config/              # Configuration classes
│   │   ├── controller/          # REST controllers
│   │   ├── converter/           # Entity/DTO converters
│   │   ├── dto/                 # Data Transfer Objects
│   │   ├── exception/           # Custom exceptions
│   │   ├── model/               # JPA entities
│   │   ├── repository/          # Data access layer
│   │   ├── rest/                # External service callers
│   │   ├── service/             # Business logic layer
│   │   └── SpringBootRestclientApplication.java
│   └── resources/
│       ├── application.yml      # Application configuration
│       └── application.properties
└── test/                        # Test classes
```

## 🔌 API Endpoints

### Basic Endpoints

| Method | Endpoint      | Description           |
|--------|---------------|-----------------------|
| GET    | `/test`       | Health check endpoint |
| GET    | `/h2-console` | H2 database console   |

### User Management

| Method | Endpoint         | Description     |
|--------|------------------|-----------------|
| GET    | `/api/user`      | Get all users   |
| GET    | `/api/user/{id}` | Get user by ID  |
| POST   | `/api/user`      | Create new user |
| PUT    | `/api/user/{id}` | Update user     |
| DELETE | `/api/user/{id}` | Delete user     |

### Employee Management

| Method | Endpoint              | Description         |
|--------|-----------------------|---------------------|
| GET    | `/api/employees`      | Get all employees   |
| GET    | `/api/employees/{id}` | Get employee by ID  |
| POST   | `/api/employees`      | Create new employee |
| PUT    | `/api/employees/{id}` | Update employee     |
| DELETE | `/api/employees/{id}` | Delete employee     |

### External Service Integration

| Method | Endpoint                     | Description                      |
|--------|------------------------------|----------------------------------|
| GET    | `/api/findById/{id}`         | Fetch user from external service |
| GET    | `/api/entityFindById/{id}`   | Fetch user with ResponseEntity   |
| GET    | `/api/user-not-found/{id}`   | Error handling example           |
| GET    | `/api/userWithExchange/{id}` | Custom exchange handling         |
| POST   | `/api/createUser`            | Create user in external service  |

## ⚙️ Configuration

### Application Properties

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: create-drop
  h2:
    console:
      enabled: true

user:
  service:
    url: https://jsonplaceholder.typicode.com
```

### Database Configuration

- **Type**: H2 In-Memory Database
- **URL**: `jdbc:h2:mem:testdb`
- **Console**: Enabled at `/h2-console`
- **DDL**: Auto-create and drop on restart

## 💾 Database

### Entities

#### User2 Entity

- `id`: Primary key (auto-generated)
- `firstName`: User's first name
- `lastName`: User's last name
- `email`: User's email (unique)

#### Employee2 Entity

- `id`: Primary key (auto-generated)
- `firstName`: Employee's first name
- `lastName`: Employee's last name
- `email`: Employee's email (unique)

### Repositories

- `UserRepository`: JPA repository for User2 entities
- `EmployeeRepository`: JPA repository for Employee2 entities
- `EmployeeJdbcRepository`: JDBC-based repository implementation

## 🧪 Testing

### Running Tests

```bash
./mvnw test
```

> **Note:** `SpringBootRestclientApplicationTests` and `RestClientServiceTest` are integration-style tests that call
> live HTTP services (the app on `http://localhost:8080` and external APIs), so they are annotated with `@Disabled`
> and skipped in CI. Start the required services and remove `@Disabled` to run them locally.

### Manual Testing

Use curl or Postman to test the endpoints:

```bash
# Test basic endpoint
curl http://localhost:8081/test

# Get all users
curl http://localhost:8081/api/user

# Test external service integration
curl http://localhost:8081/api/findById/1
```

## 🔧 RestClient Examples

### Basic RestClient Usage

```java

@Service
public class RestClientService {
    private final RestClient restClient;

    public RestClientService() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:8080")
                .build();
    }

    public EmployeeDto createEmployee() {
        EmployeeDto newEmployee = new EmployeeDto();
        newEmployee.setFirstName("John");
        newEmployee.setLastName("Doe");
        newEmployee.setEmail("john.doe@example.com");

        return restClient.post()
                .uri("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .body(newEmployee)
                .retrieve()
                .body(EmployeeDto.class);
    }
}
```

### RestClient with Error Handling

```java
public User getUserWithErrorHandling(String id) {
    return restClient.get()
            .uri("/users/{id}", id)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                throw new CustomException("User not found");
            })
            .body(User.class);
}
```

### RestClient with Exchange

```java
public User getUserWithExchange(String id) {
    return restClient.get()
            .uri("/users/{id}", id)
            .exchange((request, response) -> {
                if (!response.getStatusCode().is2xxSuccessful()) {
                    throw new CustomException("Request failed");
                }

                // Jackson 3: tools.jackson.databind.ObjectMapper (no checked IOException)
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.getBody(), User.class);
            });
}
```

## 🔄 CI/CD & Dependency Management

### GitHub Actions

The [`Java CI with Maven`](.github/workflows/maven.yml) workflow runs on every push and pull request to `main`
(and can be triggered manually via `workflow_dispatch`). It:

- Sets up **Temurin JDK 25** with Maven dependency caching
- Builds and tests the project with the Maven Wrapper (`./mvnw -B package`)
- Cancels superseded runs on the same branch (`concurrency`) and times out after 30 minutes
- Submits the Maven dependency graph to GitHub on pushes to `main`, improving Dependabot security alerts

### Dependabot

[`dependabot.yml`](.github/dependabot.yml) keeps dependencies up to date:

| Ecosystem      | Schedule                        | Grouping                                                                   | Commit prefix                     |
|----------------|---------------------------------|----------------------------------------------------------------------------|-----------------------------------|
| Maven          | Daily, 05:00 Asia/Jakarta       | `spring-boot`, `apache-httpcomponents`, `maven-plugins-and-tooling`, `minor-and-patch` | `chore(deps)` / `chore(deps-dev)` |
| GitHub Actions | Weekly (Monday), 05:00 Asia/Jakarta | All actions in a single PR                                              | `ci(deps)`                        |

Pull requests are labelled (`dependencies`, `java` / `github_actions`) and assigned automatically, and their
titles follow [Conventional Commits](https://www.conventionalcommits.org/).

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes using [Conventional Commits](https://www.conventionalcommits.org/) (`git commit -m 'feat: add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**Hendi Santika**

- Email: hendisantika@gmail.com
- Telegram: @hendisantika34
- GitHub: [@hendisantika](https://github.com/hendisantika)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- JSONPlaceholder for providing the test API
- H2 Database for the lightweight in-memory database solution
