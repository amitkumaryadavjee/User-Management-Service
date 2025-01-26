# User Management Spring Boot Application

This project is a Spring Boot-based application for managing users, integrating data from external API to load data from the external dataset into local in-memory DB, and then provide REST endpoints to fetch the users based upon various user criteria, and providing CRUD operations for user entities. The system is designed to follow modular and scalable practices, ensuring clean separation of concerns.

## **Table of Contents**
- [Architecture Overview](#architecture-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Pre-requisites](#pre-requisites)
- [Setup Instructions](#setup-instructions)
    - [Clone the Repository](#1-clone-the-repository)
    - [Build the Project](#2-build-the-project)
    - [Run the Project](#3-run-the-project)
- [Database Details](#database-details)
- [Docker Image Creation](#docker-image-creation)
- [ACCESSING THE API DOCUMENTATION](#Accessing-the-API-Documentation)
- [Future Enhancements](#future-enhancements)

---

## **Architecture Overview**

The application is designed with the following components:
- **Controller Layer**: Handles HTTP requests and maps them to appropriate service methods.
- **Service Layer**: Contains business logic for managing users, including validation and mapping.
- **Repository Layer**: Handles persistence using Spring Data JPA.
- **Validation**: Ensures input data is valid using `UserDto` and `UserValidator`.
- **Error Handling**: Uses a centralized `ResponseCodeHandler` to handle exceptions Globally gracefully and Custom `ServiceResponseException` for Custom Exception management .
- **External API Integration**: Fetches user data from external sources (`https://dummyjson.com/users`) and loads it into the database.

---

## **Features**

1. **CRUD Operations**: Create, read, update, and delete users.
2. **External API Integration**: Fetch users from an external API and persist them into the database.
3. **Validation**: Validates input data using `DTO` and custom validation logic.
4. **Error Handling**: Centralized exception handling for user-friendly error messages.
5. **H2 Database**: An in-memory database for local development.
6. **Caching and Resilience**:
    - **Caching**: Reduces redundant external API calls.
    - **Retry Mechanism**: Handles transient failures during external API calls.

---

## **Technologies Used**

- **Java**: 21
- **Spring Boot**: 3.3.0
- **Database**: H2 (in-memory for development)
- **Build Tool**: Gradle 8.x
- **Testing**: JUnit 5 (via Spring Boot Starter Test)
- **API Documentation**: SpringDoc OpenAPI (Swagger)

---

## **Pre-requisites**

1. **Java 21** or higher installed.
2. **Gradle** for building the project.
3. **Postman** or **curl** for testing API endpoints.
4. **Docker** (optional, for containerization).

---

## **Setup Instructions**

### **1. Clone the Repository**
git clone https://github.com/amitkumaryadavjee/User-Management-Service.git

### **2. Build the Project**
Build the project using Gradle:  ./gradlew build
3. Run the Project
Start the application locally: ./gradlew bootRun
The application will be accessible at:
Base URL: http://localhost:8080


### **Database Details**
Type: H2 (in-memory database)
Access the H2 Console:
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
Testing the Application
Run tests using Gradle:

### *docker image creation**

Build the Docker image for the application:
docker build -t user-management:1.0.0 .

#### **Run Docker Container**
Start the application container:
docker run -p 8080:8080 -d user-management:1.0.0
Push Docker Image (Optional)

Push the image to a container registry (e.g.,erc, Docker Hub):
docker tag user-management:1.0.0 your-dockerhub-username/user-management:1.0.0
docker push your-dockerhub-username/user-management:1.0.0


### **Accessing the API Documentation**
Start your Spring Boot application.
Open your browser and navigate to:
Swagger UI: http://localhost:8080/swagger-ui/index.html
OpenAPI documentation: http://localhost:8080/v3/api-docs

### **Future Enhancements**
Role-Based Security:

Implement Spring Security to restrict API access based on roles (admin vs. user).
Production Database:

Migrate from H2 to MySQL or PostgreSQL for a production environment.
Cloud Deployment:

Deploy the application to AWS ECS, Azure, or Kubernetes.
Enhanced APIs:

Add filtering and sorting options for user data.
Monitoring and Metrics:

Integrate tools like Prometheus and Grafana for monitoring.
