# Spring Mail Sender Sample

This project is a Spring Boot application for sending emails using HTML templates.

## Requirements

- Java Development Kit (JDK) 17 or higher.
- Apache Maven for dependency management.

## Technologies Used

- **Spring Boot**: Framework for building Java applications.
- **Thymeleaf**: Template engine for creating HTML emails.
- **Jakarta Mail**: API for sending emails.
- **Spring Web**: For building web applications with Spring.
- **Springdoc OpenAPI**: For generating API documentation with Swagger UI.
- **Lombok**: Library for reducing boilerplate code in Java.
- **Spring Boot Starter Validation**: For validating REST API request data.

## Installation

1. Clone this repository to your local machine:

```bash
git clone https://github.com/jeremw264/spring-mail-sender-sample.git
```

2. Navigate to the project directory: :

```bash
cd spring-mail-sender-sample/
```

3. Compile the project using Maven: :

```bash
mvn clean install
```

4. Run the application using Maven :

```bash
mvn spring-boot:run
```

The application will be available at [http://localhost:3001](http://localhost:3001).

## Swagger Documentation

The API documentation is available via Swagger at http://localhost:3001/swagger-ui/index.html#/

## Usage

### Send a Raw Email

To send a raw email, make a POST request to the endpoint `/email/raw` with a JSON request body:

```json
{
  "to": "recipient@example.com",
  "subject": "Test Subject",
  "content": "Test Content"
}
```

### Send a Template Email

To send an email using a template, make a POST request to the endpoint `/email/template` with a JSON request
body:

```json
{
  "to": "recipient@example.com",
  "name": "Recipient Name",
  "companyName": "Company Name"
}
```

## Configuration

Make sure to configure the following properties in your `application.yml`:

```yml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: <mail@gmail.com>
    password: <password>
    protocol: smtp
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
          timeout: 5000

custom:
  mail:
    from: test@domain.com
```
