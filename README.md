# Project Name: Order Management System

### Project Purpose
This project is designed as an order management system. Users can interact with basic entities such as customer, product, order, and order items. The system provides the following functionalities:

* Customer Management: Operations related to customers (CRUD).
* Product Management: Operations related to products (CRUD).
* Order Management: Operations related to orders (CRUD).
* Order Items Management: Management of products within orders (CRUD)

## This project is designed based on Domain-Driven Design (DDD) principles. It is developed using Java and Spring Boot, and PostgreSQL is used as the database.

### Guides

### Requirements
* JDK 11 or higher
* PostgreSQL
* Maven 
* Docker

## How to Run

To run the Flight Service API locally, follow these steps:

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/oguzcihan/order-management-system.git
    ```
2. **Build and Run the Project:**

   If this is the first time the project is being loaded, use the following Docker command:
     ```bash
    docker compose -f docker-compose.yml up --build -d 
     ``` 
   This command builds the API and creates the Docker container. The API will be accessible at the SWAGGER URL: http://localhost:8080/swagger-ui/index.html.
   If the Docker image has been created and the API needs to be restarted, use the following Docker command:
    ```bash
    docker compose -f docker-compose.yml up -d 
    ```
3. **Access Swagger UI:**

   Open your web browser and go to [SWAGGER URL](http://localhost:8080/swagger-ui/index.html) to explore the API documentation using Swagger.

   To open the Test Report URL, go to the test report url located in the swagger documentation: [TEST REPORT URL](http://localhost:63342/order-management-system/target/reports/surefire.html?_ijt=thlaaghe14283nl8v2h5ffu27h&_ij_reload=RELOAD_ON_SAVE)

4. **Postman Collection:**

    [DOWNLOAD POSTMAN COLLECTION](https://github.com/oguzcihan/order-management-system/blob/master/Order%20Management%20System.postman_collection.json)

5. **Development Environment:**

    If you want to work on the project, you can use an integrated development environment (IDE) like Spring Tool Suite or IntelliJ IDEA.
   
6. **Test Report Screenshot:**

    ![](https://github.com/oguzcihan/order-management-system/blob/master/surefire_test_report.png)
