# TickIt - Backend

## Description / Requirements

TickIt is a fully functional to-do list application, with the ability to create, update and delete to-do entries, as well as the ability to "tickit" off and mark the task as completed. The application was built using a React TypeScript frontend, with a Java SpringBoot backend and a MySQL database. 

With this project, the plan was to practice and implement how to:

- Create and implement all parts of a functional fullstack application. 
- Use SpringBoot to act as a backend between a MySQL database and a React TypeScript frontend.
- Use React TypeScript as a frontend framework.
- Handle errors in both the front and backends correctly and efficiently.
- Perform CRUD operations in a fullstack format.
- Integrate React-Testing Library to test the frontend functionality. 
- Integrate Swagger-UI and Log4j into the backend, for better developer and user experience, as well as accurate and useful documentation. 

Project Requirements:

Create a backend with Spring that can create, update, read and delete todos from a MySQL database. Along with this create a frontend application with React that interacts with this backend.

Frontend:

- Ability to add, complete, delete and edit to-dos
- Typescript & testing with React Test Library
- All errors must be handled and passed on properly back to the user (toast notifications are a good way of doing this)
- Must look polished, there is an example image attached to this spec and there are hundreds of examples of good looking todo apps online

Backend:

- All endpoints must be error handled such that they return the correct status codes and messages.
- Implement a logging strategy for requests being processed (there are loads of recourse you can find via google to do this)
  - Log4j
- Use spring swagger to generate documentation for your API

---

## Build Steps


**1. Install and configure prerequisites:**

- Java Development Kit: https://www.oracle.com/au/java/technologies/downloads/
- Apache Maven: https://maven.apache.org/download.cgi
- MySQL: https://dev.mysql.com/downloads/installer/

**2. Clone the repository and navigate into the project directory:**

```bash
git clone https://github.com/iHeslop/TickIt-backend.git
```
```bash
cd TickIt-backend
```
**3. Set up the database:**

- Log in to MySQL (can also be done within the MySQL workbench)

```bash
mysql -u root -p
```

- Create a new database:

```sql
CREATE DATABASE your_database_name;
```

**4. Configure the application properties:**

- Navigate to the src/main/resources directory

- Create an **application.properties** file and add the following configuration:

```properties
spring.application.name=tickit-backend
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

**5. Install Dependencies:**

```sh
mvn clean install
```

**6. Run the application:**

- Use the following command to run the application:

```sh
mvn spring-boot:run
```

**7. The application should now be up and running:**

- Navigate to http://localhost:8080/entries to check if its working.

**8. View Swagger Documentation:**

- Navigate to http://localhost:8080/swagger-ui/index.html to view Swagger documentation.

**Note:** To use this application with the frontend, you must have the frontend setup and running correctly. 
The link to the frontend repository: https://github.com/iHeslop/TickIt-frontend

---

## Design Goals / Approach

- I wanted the application to function as expected, with complete CRUD operations from the frontend to database.
- Correct and efficient error-handling, so that the application can run and function as required.
- I wanted to incorporate logging into the application structure, so that i had clear details of what my application was doing at all times (using Log4j).
- Use of Swagger to generate API documentation for ease of use. 

---

## Features

- **Create Entries:** Ability to create a to-entry.
- **Update and Delete:** Ability to update and delete to-do entries.
- **Logging:** Updates, errors and changes in the backend are logged to the command line while running the application.
- **Error-Handling:** Errors return the correct status and messages back to the user. 
- **Swagger:** Swagger-UI incorporated for API documentation. Allows for a simple and easy documentation of how to operate the API.

---

## Technologies:

- **Java**
- **SpringBoot**
- **MySQL**
- **Swagger**
- **Log4j**

---

## Future Goals

- Search feature
- More in-depth sort and filtering systems
- Add categories to the entries
- User Authentication
- Deploy application

---

## Struggles

- Struggle setting up swagger correctly, had issues with the correct dependencies.
- Incorporation of proper logging to the console. 

---

## More Information

- This project is paired with the frontend repository: https://github.com/iHeslop/TickIt-frontend

---
