# Rest-Assured-Dmoney-API-Testing


## Technology and Tool Used
- rest-assured
- commons-configuration
- jackson-databind
- TestNG
- Java
- Gradle
- intellij idea 

## Scenerio
##### Collection:
##### https://api.postman.com/collections/1844288-143eb923-423f-4c91-a198-fe6e56d20e35?access_key=PMAT-01GJ3CC22Q0066PJWP3T0XHQ8G
1. Create a customer and agent
2. Login with the customer
3. Update phone number of the customer
4 Give 2000 tk to the agent from System account
5. Cash in tk 1000 to the customer  from the agent account
6. Check balance of customer and agent
7. Cashout 500 tk from the customer
8. Check the balance of customer again

## Prerequisite
- JDK 8 or higher
- java IDE
- configure JAVA_HOME and GRADLE_HOME

## How to run this project
- clone this project
- hit the following command into the terminal:
  `gradle clean test`

## Command for Allure Report
- After run the project give the following command for generate Allure Report

  `allure generate allure-results --clean -o allure-report`
  
  `allure serve allure-results`  
  
## Allure Report  
![Screenshot (10)](https://user-images.githubusercontent.com/29010350/205638378-936b0eed-c65f-4678-8c1b-17bb2d2ed442.png)

  
## Gradle Report
![Screenshot (80)](https://user-images.githubusercontent.com/29010350/205562180-f5e0b666-cbe7-4489-8d6b-ecab0a38de4d.png)

