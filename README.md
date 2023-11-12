# Vector Vision Institute - Online Learning Management System

Vector Vision Institute is an Online Learning Management System (LMS) built using Java and Spring Boot. It serves as a comprehensive platform for individuals interested in technology courses. 
The application provides a seamless experience for course registration, class management, user profiles, and effective communication between users and administrators.

## Features

### User:

- **Registration & Login**
- **Viewing user profiles with enrolled courses and class information**
- **Updating user information through their profile**

### Admin:

- **Login**
- **Managing classes: Adding, deleting, and modifying class details**
- **Adding and deleting students to classes**
- **Creating and managing courses (adding students and setting fees)**
- **Viewing messages sent by users through the contact page**

## Security

The application incorporates robust security features to protect user data and ensure a safe learning environment. The security measures include:

### User Authentication

- User authentication is handled using Spring Security.
- Passwords are securely encrypted using the BCryptPasswordEncoder.

### Role-based Access Control

- Role-based access control is implemented for different user roles: **ADMIN** and **STUDENT**.
- Users with the **STUDENT** role have restricted access, while **ADMIN** users enjoy elevated privileges.

### Endpoints Access Control

- Access to various endpoints is meticulously controlled based on user roles.
- Publicly accessible endpoints include registration, login, home, holidays, contact, saving messages, assets, courses, about, and logout.
- Authenticated users have access to their dashboard, profile display, and profile update.
- Students can access specific endpoints related to student functionalities.
- Admins can access endpoints related to message display, admin functionalities, and closing messages.

### CSRF Protection

- Cross-Site Request Forgery (CSRF) protection is implemented, with exceptions for certain publicly accessible and safe endpoints.

### Configured Security Filter Chain

- A custom Security Filter Chain is configured to enforce the specified security rules.

## Technologies:

- Java 17 or above
- Spring Boot 3.1.5
- Maven
- MySQL
- Spring Data JPA
- Spring Security
- Thymeleaf
- HTML/CSS
- Spring Web
- Spring DevTools
- Lombok
- Spring Validation
- Thymeleaf Extras Spring Security 6
- Spring JDBC
- MySQL Connector
- Spring Aspects

## Running the App

## ER-Diagram

