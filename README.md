# Inventory Management System

A secure RESTful Inventory Management System developed using Spring Boot, Spring Security, JWT Authentication, MySQL, JPA/Hibernate, and Swagger/OpenAPI.

## Features

- User registration and login
- JWT-based authentication
- Role-based authorization
- USER and ADMIN roles
- Product CRUD operations
- MySQL database integration
- JPA/Hibernate
- Global exception handling
- Swagger/OpenAPI API documentation

## Technologies Used

- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Swagger/OpenAPI
- REST APIs

## User Roles

### USER

A USER can:

- View products
- Get product details

A USER cannot:

- Create products
- Update products
- Delete products

### ADMIN

An ADMIN can:

- View products
- Create products
- Update products
- Delete products

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and generate JWT token |

### Products

| Method | Endpoint | Access |
|---|---|---|
| GET | `/products` | USER / ADMIN |
| GET | `/products/{id}` | USER / ADMIN |
| POST | `/products` | ADMIN |
| PUT | `/products/{id}` | ADMIN |
| DELETE | `/products/{id}` | ADMIN |

## Security

The application uses JWT authentication.

After successful login, the server generates a JWT token.

The token must be provided in the Authorization header:

```text
Authorization: Bearer <JWT_TOKEN> 