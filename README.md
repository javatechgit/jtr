<<<<<<< HEAD
=======
<<<<<<< HEAD
API to get reward points for customers- A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.  
# ex - 
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every
dollar spent between $50 and $100 in each transaction. 
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
# Used Tech Stack -
#Java8
#SpringBoot to create RESTful endpoints 
#H2 DataBase
#Maven Build tool


# API Name - retail-reward-api
# Used in-memory H2 DataBase for DB operations
# Database name - retaildb
# Tables  - RETAIL_CUSTOMERS and RETAIL_TRANSACTIONS
# h2-console available at - http://localhost:8080/h2-console
# Actuator url with all endpoints -  http://localhost:8080/actuator 
# data.sql with sql queries to insert the records in table.
# Request Input data are tested and validated to get valid response
 
# OR Use Controller's REST end points to create CustomerEntiry and TransactionEntity as below -
#> http://localhost:8080/api/retail/customers  - to create CustomerEntity 
Request data- input data is tested and validated 
{
"customerFName":"CustomerF",
"customerLName": "CustomerL"
}
Response data-
{
    "customerId": 1,
    "customerFName": "CustomerF",
    "customerLName": "CustomerL"
}

#> http://localhost:8080/api/retail/transactions - to create TransactionEntity 
Request data- input data is tested and validated
{
"customerId":"1",
"transactionDate":"2025-05-07",
"transactionAmount":"120"
}
Response data-
{
    "transactionId": 1,
    "customerId": 1,
    "transactionDate": "2025-05-07T00:00:00.000+00:00",
    "transactionAmount": 120.0
}

# Controller's end points to find Reward Points for a customer based on customer id as below -
#> http://localhost:8080/api/retail/rewards/{customerId}
ex- http://localhost:8080/api/retail/rewards/1

# Response data- 
Customer:CustomerF:1 has total Rewards Points: 90 after total transaction amounts: 120.0
=======
>>>>>>> feature
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
 
<<<<<<< HEAD
 
=======
 
>>>>>>> 4468398 (commit changes)
>>>>>>> feature
