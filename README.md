# eBankify Security

eBankify Security is a service module designed for the eBankify application, providing user management, authentication, and security features. This module is part of the larger eBankify project, focusing on handling user data securely and efficiently.

## Features

- User creation, modification, and deletion
- Secure password storage
- UserDTO data transfer object for handling user data
- Entity and DTO merging utilities
- Custom error handling

## Technologies Used

- **Java 17+**
- **Spring Framework** (for dependency injection and service management)
- **Lombok** (for boilerplate code reduction)
- **JUnit** (for unit testing)
- **Maven** (for project management and dependencies)

## Getting Started

To run this module locally, follow these steps:

### Prerequisites

1. **Java 17 or later** installed on your machine.
2. **Maven** installed for dependency management and building the project.
3. An IDE such as **IntelliJ IDEA** or **Eclipse** for development.

### Clone the Repository
  ```bash
  git clone https://github.com/yourusername/ebankify_security.git
  cd ebankify_security
```

### Build the Project
Use Maven to build the project:

  ```bash
  mvn clean install
  ```

### Running the Application
To run the application, use the following Maven command:

  ```bash
  mvn spring-boot:run
  ```
This will start the Spring Boot application and make it accessible via the default port (8081).

### Contributing
1. Fork the repository.
2. Create your branch (git checkout -b feature/your-feature).
3. Commit your changes (git commit -am 'Add new feature').
4. Push to the branch (git push origin feature/your-feature).
5. Create a new Pull Request.
