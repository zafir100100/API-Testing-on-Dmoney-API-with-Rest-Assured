# API-Testing-on-Dmoney-API-with-Rest-Assured

## What is Rest Assured in API Automation?

Rest Assured is an open-source Java library used to test RESTful APIs. It provides a domain-specific language (DSL) for writing API tests in a simple and readable format. Rest Assured supports various HTTP methods such as GET, POST, PUT, DELETE, etc., and allows us to set request headers, query parameters, and request bodies. It also provides easy-to-use assertion methods to validate the response received from the server. Rest Assured is widely used in the industry for API testing due to its simplicity and effectiveness.

## Why Rest Assured is popular choice for API Testing?

There are several reasons why Rest Assured is a popular choice for API testing:

- Easy to Use: Rest Assured provides a simple and intuitive syntax for writing API tests, making it easy for both developers and testers to use.

- Comprehensive Functionality: Rest Assured supports various HTTP methods and provides a wide range of assertions, making it easy to test various aspects of an API such as response status codes, response headers, response body, and more.

- Integration: Rest Assured can be easily integrated with popular testing frameworks like JUnit and TestNG, making it easy to incorporate API tests into existing test suites.

- Automation: Rest Assured allows for the automation of API tests, which can save time and resources by automating repetitive tasks and increasing test coverage.

- Open-source: Rest Assured is an open-source library, which means it's free to use and has an active community of developers contributing to its development and maintenance.

## Technology Used:

- Rest Assured
- commons-configuration
- Jackson Databind
- TestNG
- Java
- Gradle
- intellij idea
- Allure

## How to run this project:

- Clone this project
- hit the following command: ```gradle clean test```
- for Allure Report hit: ```allure generate allure-results --clean -o allure-report``` and ```allure serve allure-results```

## Project Scenerio: 

 - Call login API
 - Create  a new customer and an agent
 - Search by the customer phone number
 - Deposit 5000 tk to the Agent from system
 - Deposit 2000 tk by agent to customer 
 - Check balance of customer
 - Check statement by trnxId 
 - Withdraw 1000 tk by customer and assert expected balance
 - Send 500 tk to another customer and assert expected balance
 - Check customer statement
  
## Allure Report  
![Screenshot (10)](https://user-images.githubusercontent.com/29010350/205638378-936b0eed-c65f-4678-8c1b-17bb2d2ed442.png)

  
## Gradle Report
![Screenshot (80)](https://user-images.githubusercontent.com/29010350/205562180-f5e0b666-cbe7-4489-8d6b-ecab0a38de4d.png)

