package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Config;
import utils.ExtentReportManager;

import java.util.HashMap;
import java.util.Random;

import static io.restassured.RestAssured.*;

public class CustomerWorkflowTest {

    static int customerId;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @Test(priority = 1)
    public void createCustomer() {
        test = extent.createTest("Create Customer", "Create a new customer in the system");

        RestAssured.baseURI = Config.BASE_URL;

        // Generate random email and username
        String randomString = generateRandomString(8);
        String email = randomString + "@example.com";
        String username = "user_" + randomString;

        // Create user data using HashMap
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("first_name", "John");
        requestBody.put("last_name", "Doe");
        requestBody.put("username", username);
        requestBody.put("password", "password123");

        test.log(Status.INFO, "Request body created: " + requestBody);

        // Send POST request to create a customer
        Response response = given()
                .auth().preemptive().basic(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().post()
                .then().statusCode(201).extract().response();

        customerId = response.jsonPath().getInt("id");
        test.log(Status.PASS, "Customer created successfully. ID: " + customerId);

        Assert.assertNotNull(customerId, "Customer ID should not be null");
    }

    @Test(priority = 2, dependsOnMethods = "createCustomer")
    public void retrieveCustomer() {
        test = extent.createTest("Retrieve Customer", "Retrieve details of the created customer");

        Response response = given()
                .auth().preemptive().basic(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .when().get(Config.BASE_URL + customerId)
                .then().statusCode(200).extract().response();

        String retrievedEmail = response.jsonPath().getString("email");
        test.log(Status.INFO, "Retrieved customer email: " + retrievedEmail);
        test.log(Status.PASS, "Customer retrieved successfully.");

        Assert.assertNotNull(retrievedEmail, "Customer email should not be null");
    }

    @Test(priority = 3, dependsOnMethods = "createCustomer")
    public void updateCustomerEmail() {
        test = extent.createTest("Update Customer", "Update the email of the created customer");

        String newEmail = "updated_" + generateRandomString(5) + "@example.com";

        // Create update data
        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", newEmail);

        test.log(Status.INFO, "Updating customer email to: " + newEmail);

        // Send PUT request to update the customer's email
        Response response = given()
                .auth().preemptive().basic(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when().put(Config.BASE_URL + customerId)
                .then().statusCode(200).extract().response();

        test.log(Status.PASS, "Customer email updated successfully.");

        String updatedEmail = response.jsonPath().getString("email");
        Assert.assertEquals(updatedEmail, newEmail, "Customer email should be updated.");
    }

    @Test(priority = 4, dependsOnMethods = "createCustomer")
    public void deleteCustomer() {
        test = extent.createTest("Delete Customer", "Delete the created customer");

        Response response = given()
                .auth().preemptive().basic(Config.CONSUMER_KEY, Config.CONSUMER_SECRET)
                .when().delete(Config.BASE_URL + customerId + "?force=true")
                .then().extract().response();

        if (response.statusCode() == 200) {
            test.log(Status.PASS, "Customer deleted successfully.");
        } else if (response.statusCode() == 404) {
            test.log(Status.INFO, "Customer is already deleted or does not exist.");
        }

        Assert.assertEquals(response.statusCode(), 200, "Customer should be deleted successfully.");
    }

    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return builder.toString();
    }

    @AfterClass
    public void tearDown() {
        extent.flush();
    }
}
