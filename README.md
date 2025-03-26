# BlogApp

## Overview

BlogApp is a backend service built using Java Spring Boot that allows users to create, retrieve, and manage blogs. It utilizes JPA for database
interactions and includes a simple AI-powered text summarization feature. The application is deployed on AWS EC2 using Docker

## Features

- CRUD operations for blog posts
- MySQL database integration
- OpenAI-based blog summarization
- Deployed using Docker on AWS EC2

## Technologies Used

- Spring Tool Suite IDE
- Java
- Spring Boot 
- Lombok
- MySQL
- Hibernate
- Docker
- AWS EC2
- OpenAI API

## Setup Instructions

### Prerequisites

Ensure you have the following installed:

- Java 17
- Maven
- MySQL
- Git
- STS /EClipse /VS Code For Running The Application

### Clone the Repository

```sh
git clone https://github.com/SandipRRathod/BlogApp.git
cd BlogApp
```

### Configure the Database

Create a MySQL database named `blogapp` and update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogapp
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### Run the Application

```sh
mvn spring-boot:run | Use An IDE For Then Run A Springboot Application
```

The server will start at `http://localhost:8080`.

## Deployed on AWS EC2

The application is accessible via:

1. [http://ec2-13-200-222-90.ap-south-1.compute.amazonaws.com](http://ec2-13-200-222-90.ap-south-1.compute.amazonaws.com/)
2. [https://ec2-13-200-222-90.ap-south-1.compute.amazonaws.com](http://ec2-13-200-222-90.ap-south-1.compute.amazonaws.com/)


**Note :- If the second is not working, then use the first or vice versa.**

## API Endpoints

### Blog Management

| Method | Endpoint                   | Description                   |
| ------ | -------------------------- | ----------------------------- |
| POST   | `/api/addblog`             | Add a new blog                |
| DELETE | `/api/deleteblog/{id}`     | Delete a blog by ID           |
| PUT    | `/api/updateblog/{id}`     | Update a blog by ID           |
| GET    | `/api/getblog/{id}`        | Get a single blog by ID       |
| GET    | `/api/getallblog/{pageno}` | Get all blogs with pagination |

### AI-Powered Blog Summarization

| Method | Endpoint                 | Description                                                                        |
| ------ | ------------------------ | ---------------------------------------------------------------------------------- |
| GET    | `/api/ai/summarize`      | Summarize provided blog content                                                    |
| GET    | `/api/ai/summarize/{id}` | Summarize blog content by blog ID                                                  |

## Project Dependencies (From `pom.xml`)

This project is built with the following dependencies:

- **Spring Boot Starter Web**: Provides core functionalities for building RESTful web services.
- **Spring Boot Starter Data JPA**: Enables database interactions using JPA and Hibernate.
- **Spring Boot DevTools**: Provides development tools for live reloading and better debugging.
- **Spring Boot Starter Validation**: Supports validation for request objects.
- **MySQL Connector Java**: Enables communication with the MySQL database.
- **Lombok**: Reduces boilerplate code with annotations like `@Getter`, `@Setter`, etc.
- **Spring AI OpenAI Starter**: Integrates OpenAI for blog summarization features.
- **Spring Boot Starter Test**: Provides testing support for the application.
- **Docker**: Used for containerizing the application for deployment.
- **AWS EC2**: The application is hosted on an EC2 instance for accessibility.

The project uses **Java 17** and **Maven** as the build tool.

## Additional Notes

- Every API endpoint starts with `/api` as the base path.
- The application is designed for scalability and can be easily extended.
- Docker ensures smooth deployment and consistency across environments.
- Future improvements may include authentication and authorization mechanisms for better security.

