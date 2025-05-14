# Retail Reward Api - Spring Boot application
Retail-Reward-Api is spring boot based Rest service application.

## Technologies Stack: 
 - Java 1.8
 - Maven 3.9.5
 - Spring Boot (version 3.4.5)
 - Tomcat 10.1.40 embedded
 - In-memory DataBase H2 (version 2.3.232)
 
## About the Api service: 
 Retail-Reward-Api is Rest service application that can be used to - Create Customers,Create Transactions and to get All Transactions like - Last Month Transactions, Second Last Month Transactions, Third Last Month Transactions and to find All Reward Points of last three months transaction for a customer based on by customer id. This Rest API mainly to get all reward points of a customer based on purchase criteria.
 
 - The Rest service is running at port 9090 and any Rest endpoint is accessible at this port.
 - To run,need to add Environment variables from application.properties in Run configurations.
 - The Rest service using in-memory H2 database for CRUD operations, db details as below -
    - h2 console at http://localhost:9090/h2-console to view and query the database.
    - database is retaildb
    - Tables used to read and write - RETAIL_CUSTOMER and RETAIL_TRANSACTIONS
    - DB informations settings in application.properties file using Environment variables.
    - The api has data.sql file with sql query- to create required tables insert the records.
    
 - The Rest service api supports JSON data type for request and response.  
 - This api service used Spring Data JPA for ORM support.
 - This api used custom exception handle.
 - Unit testing using Junit and Mockito libraries.  
 
-Actuator to Get API informations using actuator like- health, info ,env, metrics using below endpoints at other different port 9091.
  - http://localhost:9091/actuator
  - http://localhost:9091/actuator/health
  - http://localhost:9091/actuator/info
  - http://localhost:9091/actuator/env
  - http://localhost:9091/actuator/metrics 
   
## Api Endpoints url, Api Request/Response data structure, Api Response with success and errors: 
This api has endpoints to handle POST and GET requests.
 - POST endpoint url to CREATE Customer - http://localhost:9090/api/retail/customers 
    -Request body data format to create customer -
      - {
         "customerFName":"Ril",
         "customerLName": "Ret"
        }
    - Response body data format - RESPONSE: HTTP 201 (Created)
        - {
         "customerId": 2,
         "customerFName": "Ril",
         "customerLName": "Ret"
          }
      -   
        
 - POST endpoint url to CREATE transaction - http://localhost:9090/api/retail/transactions
       - Request body data format to create customer -
       - {
         "customerFName":"Ril",
         "customerLName": "Ret"
        }
    - Response body data format - RESPONSE: HTTP 201 (Created)
        - {
         "customerId": 2,
         "customerFName": "Ril",
         "customerLName": "Ret"
         }
 - GET  endpoint url to get all transaction and reward points for customer using customer id as path variable - http://localhost:8080/api/retail/rewards/{customerId} ex# for customer id=1 http://localhost:8080/api/retail/rewards/1 
     - Response body data format -  RESPONSE: HTTP 200 (OK)  
        - {
        "success": "Get All Transactions Successfully for Customer:",
        "customerId": 1,
        "customerFName": "CustomerFName",
        "customerLName": "CustomerLName",
        "lastMonthTransactionsAmt": 240.0,
        "lastSecondMonthTransactionsAmt": 0.0,
        "lastThirdMonthTransactionsAmt": 0.0,
        "totalTransactionsAmt": 240.0,
        "lastMonthRwdPoints": 190,
        "lastSecondMonthRwdPoints": 0,
        "lastThirdMonthRwdPoints": 0,
         "totalRewardPoints": 190
        }
 
 