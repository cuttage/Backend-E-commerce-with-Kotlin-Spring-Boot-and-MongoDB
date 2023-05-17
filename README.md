# Spring Boot Kotlin E-commerce Application

This is a simple Spring Boot Kotlin application for an e-commerce system. It provides user registration and login functionality using MongoDB for data storage and JSON Web Tokens (JWT) for authentication.

Please note that this application is a test project and might have some debugging issues. The API endpoints are currently returning a 404 error.

## Prerequisites

Before running the application, ensure that you have the following:

- Java Development Kit (JDK) installed (version 17 or higher)
- MongoDB server installed (separate installation required)
- Permission from the author to access the MongoDB server with a specific IP address and a user account with a password

## Running the Application

Follow the steps below to run the Spring Boot Kotlin application on your local machine.

### MongoDB Setup

1. Install MongoDB on your local machine by following the official MongoDB installation guide: [MongoDB Installation Guide](https://www.mongodb.com/docs/manual/installation/).

2. Once MongoDB is installed, start the MongoDB server.

### Application Setup

1. Clone the project repository.

2. Open a terminal or command prompt and navigate to the project's root directory.

3. Create environment variables for MongoDB username and password. Replace `username` and `password` with the actual values provided to you by the author.

- #### Unix/Linux/macOS:

```bash
export MONGODB_USERNAME=username
export MONGODB_PASSWORD=password
```

- #### Windows (Command Prompt):

```bash
set MONGODB_USERNAME=username
set MONGODB_PASSWORD=password
```

- #### Windows (PowerShell):

```bash
$env:MONGODB_USERNAME = "username"
$env:MONGODB_PASSWORD = "password"
```

4. Verify that the environment variables are set correctly by running the following commands:

- #### Unix/Linux/macOS:

```bash
echo $MONGODB_USERNAME
echo $MONGODB_PASSWORD
```

- #### Windows (Command Prompt):

```bash
echo %MONGODB_USERNAME%
echo %MONGODB_PASSWORD%
```

- #### Windows (PowerShell):

```bash
echo $env:MONGODB_USERNAME
echo $env:MONGODB_PASSWORD
```

Ensure that the output displays the expected values.

5. Update the `application.properties` file located in the `src/main/resources` directory. Replace `${System.getenv("MONGODB_USERNAME")}` and `${System.getenv("MONGODB_PASSWORD")}` with the environment variables you set in the previous step.

Example `application.properties`:

```kotlin
spring.data.mongodb.uri=mongodb+srv://${System.getenv("MONGODB_USERNAME")}:${System.getenv("MONGODB_PASSWORD")}@ecommerce.xcrsaqr.mongodb.net/Ecommerce?retryWrites=true&w=majority
app.jwtSecret=ecom
app.jwtExpirationInMs=86400000
```

### Running the Application

1. Open a terminal or command prompt and navigate to the project's root directory.

2. Build the application using the following command:

```bash
./gradlew build
```

3. Once the build is successful, run the application with the following command:

```bash
./gradlew bootRun
```

4. The application will start running and listen on `http://localhost:8080`.

## API Endpoints

Please note that this application is currently experiencing debugging issues, and the API endpoints are returning a 404 error. The following API endpoints are available:

### User Registration

- #### Endpoint:

  `POST /api/users/register`

- #### Description:

  Register a new user.

- #### Request Body:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

- #### Response:
  Returns the registered user object.

### User Login

- #### Endpoint:

  `POST /api/users/login`

- #### Description:

  Log in a user and obtain an access token.

- #### Request Body:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

- #### Response:
  Returns the access token.

## Debugging

As mentioned earlier, this application currently has debugging issues, and the API endpoints may return a 404 error. You may need to investigate and fix the root cause of the issue. Review the code and logs to identify any potential errors or misconfigurations.

Please reach out to the author of this application for further assistance and to obtain permission for your IP address to be included in the MongoDB server's access list. Additionally, ask the author to create a user account for you with a password that you can use as environment variables to access the database.
