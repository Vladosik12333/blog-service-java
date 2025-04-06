# Blog Service Java

The Java service serves as a REST API for social network allowing CRUD operations
on posts and reactions. Also, it includes authentication and authorization functionality
using a JWT token.

## Screenshots

![Image 1](images/img.png)
![Image 2](images/img_1.png)
![Image 3](images/img_2.png)
![Image 4](images/img_3.png)

## Installation

1. Clone repository `git clone https://github.com/Vladosik12333/blog-service-java`
2. Create env file for secrets `touch .env`
3. Put in env file: DB_URL, DB_USER, DB_PASSWORD, DB_NAME, JWT_TOKEN_SECRET
4. Run docker compose `docker-compose up`
5. Open [swagger](http://localhost:8080/api/swagger-ui/index.html) to test the service
6. In case of testing from Postman,
   import [Postman collection](blog-service-java.postman_collection.json)
7. Use GET /health-check/ping expecting response "Success" 200 to verify the service running

> Use login "admin" and password "admin" to log in.

## Technologies

<p align="left">
<img src="https://img.shields.io/badge/Language-Java 23-orange.svg" alt="Language - Java 23">
<img src="https://img.shields.io/badge/Framework-Spring Boot 3.4.2-green.svg" alt="Framework - 
Spring Boot 3.4.2">
<img src="https://img.shields.io/badge/Spring Boot-Web MVC-green.svg" alt="Spring Boot - Web">
<img src="https://img.shields.io/badge/Spring Boot-Data JPA-green.svg" alt="Spring Boot - 
Data JPA">
<img src="https://img.shields.io/badge/Spring Boot-Security-green.svg" alt="Spring Boot - 
Security">
<img src="https://img.shields.io/badge/Spring Boot-Test-green.svg" alt="Spring Boot - Test">
<img src="https://img.shields.io/badge/Spring Boot-Validation-green.svg" alt="Spring Boot 
- Validation">
<img src="https://img.shields.io/badge/Library-JUnit-red.svg" alt="Library - JUnit">
<img src="https://img.shields.io/badge/Library-Mockito-red.svg" alt="Library - Mockito">
<img src="https://img.shields.io/badge/Library-Lombok-red.svg" alt="Library - Lombok">
<img src="https://img.shields.io/badge/Library-Liquibase-red.svg" alt="Library - Liquibase">
<img src="https://img.shields.io/badge/Library-OpenAPI-red.svg" alt="Library - OpenAPI">
<img src="https://img.shields.io/badge/Library-jsonwebtoken-red.svg" alt="Library - jsonwebtoken">
<img src="https://img.shields.io/badge/Library-h2database-red.svg" alt="Library - h2database">
<img src="https://img.shields.io/badge/Library-logbook-red.svg" alt="Library - logbook">
<img src="https://img.shields.io/badge/Library-dotenv-red.svg" alt="Library - dotenv">
<img src="https://img.shields.io/badge/Database-PostgreSQL-blue.svg" alt="Database - PostgreSQL">
<img src="https://img.shields.io/badge/Technology-Docker-blue.svg" alt="Technology - Docker">
</p>

## Authors

[Vladyslav Babiak - Developer](https://github.com/Vladosik12333)

## Copyright & Licensing Information

[MIT License](LICENSE)
