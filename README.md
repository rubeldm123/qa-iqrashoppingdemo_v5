QA-IqraShoppingDemo v1.5
Overview
The QA-IqraShoppingDemo project demonstrates REST API testing using RestAssured and ExtentReports for report generation. This project automates the CRUD operations for WooCommerce Customers API with detailed HTML reports for test results.

Features
    1. API Testing:

        > Create a new customer.
        > Retrieve the created customer.
        > Update the customer’s email.
        > Delete the customer.

    2. ExtentReports Integration:

        > Generates visually appealing HTML reports.
        > Logs test details for better analysis.
        > Displays test results in a structured format.

    3. Reusable Configuration:

        >Config.java centralizes the base URL, consumer key, and consumer secret for all tests.

    4. Randomized Data:

        >Generates random email addresses and usernames to ensure unique test data.

Project Structure
    qa-iqrashoppingdemo_v5/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/
│   │           ├── Config.java
│   │           └── ExtentReportManager.java
│   ├── test/
│   │   └── java/
│   │       └── tests/
│   │           └── CustomerWorkflowTest.java
├── pom.xml
└── README.md

Build the Project
   > mvn clean install


Here is the README.md file for Version 5 (v1.5) with ExtentReports integration:

QA-IqraShoppingDemo v1.5
Overview
The QA-IqraShoppingDemo project demonstrates REST API testing using RestAssured and ExtentReports for report generation. This project automates the CRUD operations for WooCommerce Customers API with detailed HTML reports for test results.

Features
API Testing:

Create a new customer.
Retrieve the created customer.
Update the customer’s email.
Delete the customer.
ExtentReports Integration:

Generates visually appealing HTML reports.
Logs test details for better analysis.
Displays test results in a structured format.
Reusable Configuration:

Config.java centralizes the base URL, consumer key, and consumer secret for all tests.
Randomized Data:

Generates random email addresses and usernames to ensure unique test data.
Project Structure
css
Copy code
qa-iqrashoppingdemo_v5/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utils/
│   │           ├── Config.java
│   │           └── ExtentReportManager.java
│   ├── test/
│   │   └── java/
│   │       └── tests/
│   │           └── CustomerWorkflowTest.java
├── pom.xml
└── README.md
Key Components
Config.java:

Centralizes the configuration for API testing.
Contains the base URL, consumer key, and consumer secret.
ExtentReportManager.java:

Manages the initialization and configuration of ExtentReports.
Ensures all tests share the same report instance.
CustomerWorkflowTest.java:

Contains test cases for customer creation, retrieval, update, and deletion.
Uses TestNG for test execution.
Prerequisites
Java: JDK 17 or higher
Maven: Latest version
IDE: IntelliJ IDEA (or any other Java IDE)
Setup Instructions
Step 1: Clone the Repository
bash
Copy code
git clone https://github.com/your-repo/qa-iqrashoppingdemo.git
cd qa-iqrashoppingdemo
Step 2: Update Dependencies
Ensure your pom.xml contains the required dependencies:

xml
Copy code
<dependencies>
<!-- RestAssured for API Testing -->
<dependency>
<groupId>io.rest-assured</groupId>
<artifactId>rest-assured</artifactId>
<version>5.3.0</version>
</dependency>

    <!-- ExtentReports for Report Generation -->
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.1.2</version>
    </dependency>

    <!-- JSON Serialization -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.2</version>
    </dependency>
    </dependencies>
Step 3: Build the Project

    > mvn clean install
Execution
Run Tests with Maven
    > mvn test
