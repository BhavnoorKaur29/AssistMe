# AssistMe Project

AssistMe is a microservices-based application designed to connect people seeking help with those offering assistance. The project is built using Java (Spring Boot) and includes service discovery and API gateway components for scalability and reliability.

## Project Structure

- **AssistMe**: Main service providing core application features (user registration, service requests, dashboard, etc.).
- **api-gateway**: Handles routing and request forwarding between clients and backend services.
- **eureka-server**: Service registry for microservices discovery.

## Features
- User registration and login
- Service request and offer management
- Dashboard and reviews
- Multiple service categories (childcare, cleaning, elderly care, grocery, pet care, tutoring)
- Microservices architecture with API Gateway and Eureka Service Discovery

## Prerequisites
- Java 17 or above
- Maven

## Setup Instructions
1. **Clone the repository**
   ```sh
git clone <repository-url>
```
2. **Start Eureka Server**
   ```sh
cd eureka-server
mvnw spring-boot:run
```
3. **Start API Gateway**
   ```sh
cd api-gateway
mvnw spring-boot:run
```
4. **Start AssistMe Service**
   ```sh
cd AssistMe
mvnw spring-boot:run
```

## Usage
- Access the application via the API Gateway endpoint (default: `http://localhost:8080`).
- Register or log in as a user.
- Request or offer help in various service categories.

## Configuration
- Application properties and YAML files are located in each module under `src/main/resources`.
- Update configuration as needed for database, ports, etc.

## License
This project is licensed under the MIT License.

## Authors
- Bhavnoor Kaur
- [Add other contributors here]

---
Feel free to contribute or raise issues for improvements!
