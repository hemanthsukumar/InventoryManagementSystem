Inventory Management System (IMS)

Overview
The Inventory Management System (IMS) is a full-stack application for managing inventory. It includes a backend developed in Spring Boot and a frontend built with React.js. This system provides various APIs and features for user registration, authentication, inventory management, and order processing.
Features
1. Login: Authenticate users and send a response.
2. Registration: Users can register on the IMS portal, with an option to be an admin or a regular user.
3. Get Inventory: Users can retrieve inventory data using location ID and material ID.
4. Update Inventory: Admins can add items to the inventory and update the available quantity.
5. Order: Users can place orders for items.
6. View Orders: Users can view their ordered items.
7. Cancel Order: Users can cancel an ordered item.
8. Add Item: Admins can add items to the inventory.
9. Forgot Password: User-friendly password recovery feature.
10. Change Password: Allows users to change their passwords.
Installation
Prerequisites
Ensure that you have the following prerequisites installed:
* Java Development Kit (JDK)
* Apache Maven
* Node.js
* Chrome or Firefox browser installed
Backend (Spring Boot)
1. Clone this repository.
2. Navigate to the backend directory.
3. Run mvn clean install to build and install the backend dependencies.
4. Configure the database connection in the application.properties file.
5. Run mvn spring-boot:run to start the Spring Boot API server.
Frontend (React.js)
1. Navigate to the frontend directory.
2. Run npm install to install the necessary frontend dependencies.
3. Configure the API endpoint in the frontend code if needed.
4. Run npm start to start the React.js application.
Usage
Backend API
The API is accessible at http://localhost:8080. Users and admins can interact with the API using the defined endpoints.
Frontend Application
Access the frontend application at http://localhost:3000 in your web browser. Users can register, log in, place orders, view inventory, and more.
Automated Testing
We have implemented automated tests for the Inventory Management System (IMS) using Selenium and TestNG. To run these tests, first Navigate to the frontend directory and follow the steps below:
1. Test Configuration
Open the testng.xml file in the tests directory. Customize the test suite configuration as needed, including parameters like browser type and test groups.
2. Running Tests
There are two options to run the Selenium tests:
a. Using Maven from the Command Line:
   1. Open a terminal.
   2. Navigate to the tests directory.
   3. Run the following Maven command:
mvn clean test
      3. This command will execute the tests defined in the testng.xml file.
b. Using an Integrated Development Environment (IDE):
If you're using an IDE that supports TestNG, you can right-click on the testng.xml file and select the option to run the suite.
      4. Viewing Test Results
TestNG generates detailed test reports in the test-output directory. Open the HTML report file to view test results and logs.
      5. Customization
Feel free to modify the test scripts in the src/test directory to extend or adapt them to your specific needs.
