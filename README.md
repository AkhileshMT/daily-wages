## Daily Wages Management System
This project is a Wages Management System designed to track daily expenses and manage personal spending efficiently. Users can record purchases such as groceries and other essential items,and the system automatically tracks expenses on a daily, weekly, and monthly basis. It provides insights into total money spent by weekdays and by month. The application allows users to configure spending limits for daily, weekly or monthly usage. The system continuously monitors expenses against the defined limits. When the spending exceeds the configured limit, it generates a warning notification to alert the user. This helps users control expenses and avoid overspending.

## Tech Stack
- Java  
- Spring Boot  
- Spring Security  
- REST APIs  
- SQL  
- Swagger (OpenAPI)  
- Postman
- JPA / Hibernate
- JWT Authentication
- Maven

## What does it solve?
This application solves the problem of manually tracking daily expenses and monitoring spending limits. It helps users record purchases in a structured way, analyze spending patterns over daily, weekly, and monthly periods, and prevent overspending by validating expenses against predefined limits. By providing real-time warnings when limits are exceeded, the system enables better financial control and budgeting.

## How to Run?

### Prerequisites
- Java 17 installed
- Maven installed
- MySQL installed and running
- Git installed

### Steps

1. Clone the repository
   git clone https://github.com/AkhileshMT/daily-wages

2. Navigate to the project directory
   cd daily-wages

3. Create a MySQL database
   Create a database in MySQL (for example: daily_wages_db)

4. Configure database details
   Open src/main/resources/application.properties and update the following values:

   spring.datasource.url=jdbc:mysql://localhost:3306/daily_wages_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password

5. Build the project
   mvn clean install

6. Run the application
   mvn spring-boot:run

7. Access the application
   - API base URL: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

8. Test APIs using Postman or Swagger UI

## Security Implementation

Spring Security is integrated to secure the application and protect all backend APIs. JWT-based authentication is used to ensure stateless and secure communication between the client and server. Users must authenticate using valid credentials to receive a JWT token, which is required for accessing protected endpoints. Role-based authorization is implemented to restrict access to specific APIs based on user roles. Passwords are securely stored using encryption to prevent unauthorized access. This security setup helps ensure data protection, scalability, and safe API access.

## My Contribution

- Designed and developed the backend application using Java and Spring Boot.
- Implemented RESTful APIs for managing expenses, spending limits, and reports.
- Secured the application using Spring Security with JWT-based authentication.
- Integrated MySQL database and wrote optimized SQL queries for data persistence.
- Implemented business logic for daily, weekly, and monthly expense tracking and limit validation.
- Tested APIs using Postman and documented endpoints using Swagger (OpenAPI).
