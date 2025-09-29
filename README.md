# Spring Boot RestClient Demo Application

A comprehensive demonstration of Spring Boot 3.3.3 with RestClient functionality, showcasing various HTTP client
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

- **Java**: 21
- **Spring Boot**: 3.3.3
- **Spring Framework**: 6.1.12
- **Spring Data JPA**: 3.3.3
- **Spring WebFlux**: 6.1.12
- **H2 Database**: Runtime
- **Apache HTTP Components**: 5.4.1
- **Maven**: 3.x
- **Hibernate**: 6.5.2

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Git (for cloning the repository)

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/hendisantika/spring-boot-restclient.git
cd spring-boot-restclient
```

### 2. Build the Project

```bash
mvn clean compile
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

Or alternatively:

```bash
mvn compile exec:java -Dexec.mainClass="com.hendisantika.springbootrestclient.SpringBootRestclientApplication"
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
mvn test
```

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

                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(response.getBody(), User.class);
            });
}
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
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
