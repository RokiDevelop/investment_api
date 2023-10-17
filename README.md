# Investment API
This is the README file for the "Investment API" project.

## Project Overview
The "Investment API" project is a collection of microservices designed to provide various functionalities related to investments and stock trading. These microservices work together to offer features like user authentication, portfolio management, investment performance tracking, market data retrieval, and more.

The project is built using the following technologies and tools:

Java 17
Maven
PostgreSQL
Docker
Microservices
The project consists of the following microservices:

1. Discovery Service: This service is responsible for service discovery within the application.
2. Gateway Service: The API Gateway service acts as a centralized entry point for client applications to interact with the other microservices.
3. Auth Service: This service handles user authentication, registration, JWT token generation, and token validation.
4. Portfolio Service: The Portfolio Service provides information about a user's stock portfolio.
5. Performance Tracking Service: This service calculates the performance and returns performance-related data for a user's stock portfolio.
6. Investment Market Service: This service provides information about the stock market, including stock prices, trading history, and market events.

## Installation
To install and run the project, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project directory and open a terminal.
3. Run the following command to build the project using Maven:
   ```mvn clean install```
4. After a successful build, copy the generated JAR files for each microservice to their respective directories. The directories are typically located at 
_/docker/services/api/[service-name]_
5. Install Docker on your system if it's not already installed.
6. Navigate to the /Docker directory in the project.
7. Run the following command to start the Docker containers for the microservices:
  ```docker-compose up```

## Usage
As the project does not have a user interface (UI), you'll need to interact with the microservices using tools like Postman.
