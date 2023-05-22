API Meter Service
---
The API Meter Service is a Spring Boot application that provides metering capabilities for API endpoints. It allows tracking API usage and generation of period invoice.

Prerequisites
Before running the API Meter Service, ensure you have the following installed:

1. Java Development Kit (JDK) 11
2. Apache Maven 

# Getting Started 
Follow these steps to set up and run the API Meter Service:
- Clone the repository:
``git clone https://github.com/your-username/api-meter-service.git``
Build the project:

  - ```cd api-meter-service```
- ### For Maven:
    ```mvn clean install```

- ### Run Application:
     ```mvn spring-boot:run```

Access the API Meter Service:
Once the application is running, you can access the API Meter Service at 
```http://localhost:8080```.

 # Testing
  - To run the tests for the API Meter Service, use the following Maven command:
  - ```mvn test```

 Configuration
---
The API Meter Service can be configured through the `application.properties` file. Customize the configuration based on your requirements, such as database connection details and API endpoint mappings.

- `database-config`
  - MongoDb is the database used. To configure mongodb for the app: 
  - `spring.data.mongodb.uri` add this to application.properties and update the uri with a mongodb uri 
-  `cron-billing-cycle` billing cycle scheduler has been configured to run monthly so there is no need for a change

> Usage
The API Meter Service provides the following endpoints:

- `/usage`: logs api usage.
- `/license`: Creates basic consumer information and generate the consumer ID.

Swagger Documentation
---
The API Meter Service provides Swagger documentation for easy exploration of the available API endpoints and their parameters. Follow the steps below to access the Swagger UI:

1. Make sure the API Meter Service is running locally.

2. Open your web browser and navigate to the following URL:

   ```http://localhost:8080/swagger-ui.html```

3. The Swagger UI page will open, allowing you to browse and interact with the API documentation.

### Contact
For any questions or support, please contact `taiwoOyewole329@gmail.com`.

