# Project README

## Overview

This project is a News Search microservices-based application using **Spring Boot** for the backend and **Angular** for the frontend. The application is designed to demonstrate best practices in software development, performance, security, and production readiness. It also integrates Continuous Integration (CI), Continuous Deployment (CD), and Docker-based service deployment.

## Key Features

- **SOLID Principles**: Implementation of object-oriented design principles for maintainable and extensible code.
- **12-Factor App Methodology**: Application is designed following the 12-factor principles to ensure scalability and easy deployment.
- **HATEOAS**: Hypermedia as the engine of application state (HATEOAS) for enhanced REST API discoverability.
- **Design Patterns**: Usage of design patterns such as Singleton, Factory, Strategy,Builder and Observer.
- **Performance & Optimization**: Performance optimizations for low latency, efficient data handling, and caching.

---

## Design & Architecture

### Architectural Overview

The application is designed with a microservices architecture where:

- The **Spring Boot** backend exposes RESTful APIs for business logic and data handling.
- The **Angular** frontend interacts with the backend APIs.
- Services are containerized using **Docker** and deployed locally for testing and production readiness.

**Key Design Aspects**:

- **SOLID Principles**: The code is structured to follow SOLID principles ensuring high cohesion, low coupling, and easy maintenance.
- **12-Factor App**: Each service is stateless, configuration is separated from the codebase, and logs are stored in stdout.
- **HATEOAS**: The REST API is designed to guide the client through available resources via hypermedia links.
- **Design Patterns**: Key patterns include:
  - **Singleton** for shared resources.
  - **Factory** for object creation.
  - **Strategy** for dynamic behavior changes.
  - **Observer** for event-driven architectures.


## API Documentation

There is one api used to get news:
```
GET: http://localhost:8080/news/search?keyword={abc}&page={pageNumber}&offlineMode={boolean}
```
 **keyword** is for filter the search
 **pageNumber** is the number you need to be on and default value is 1
 **offlineMode** to toggle some predefined news on local and default value is false


#### Error Codes

- **400 Bad Request**: The request was invalid.
- **401 Unauthorized**: Authentication required.
- **404 Not Found**: The resource could not be found.
- **500 Internal Server Error**: Something went wrong on the server.

---

## Build & Deployment

### CI/CD Pipeline

The project is configured with Jenkins for Continuous Integration and Deployment. The pipeline automates the process of building, testing, and deploying the services.

#### CI Pipeline

- **Build**: The code is compiled and tested using Maven/Gradle.
- **Test**: Unit tests and integration tests are executed, following TDD/BDD practices.
- **Static Analysis**: Tools like SonarQube are used for code quality checks.

Pipeline configuration script is in root directory named Jenkinsfile

#### CD Pipeline

- **Docker Deployment**: The backend service is deployed using Docker containers. A Dockerfile is included to build and run the application locally.
docker file is also located in root directory named Dockerfile

---

## Steps to start application using Jenkins
- **Step 1** Copy the Jenkins script from Jenkinsfile
- **Step 2** In jenkins web interface, Click on New Item > Enter an Item name > Select Pipeline > Advanced Project Options > paste the script
- **Step 3** Save
- **Step 4** Click Build Now
- **Step 5** http:localhost:8080 is the end point where frontend is served in springboot static files



```
Note: Make sure to have Nodejs, angular, Java and maven in your system properly configured
```

