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