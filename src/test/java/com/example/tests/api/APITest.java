package com.example.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

        @Test
        public void verifyStatusCodeOfGetRequest() {
            // Base URI
            RestAssured.baseURI = "https://reqres.in/api";

            // Send a GET request to "/users?page=2" endpoint
            Response response = RestAssured
                    .given()
                    .when()
                    .get("/users?page=2")
                    .then()
                    .extract()
                    .response();


            System.out.println("Response Body: " + response.asPrettyString());

            // Get the status code
            int statusCode = response.getStatusCode();

            // Validate the status code is 200
            Assert.assertEquals(statusCode, 200, "Expected status code is 200 but found: " + statusCode);

            System.out.println("Status code is: " + statusCode);
        }
}
