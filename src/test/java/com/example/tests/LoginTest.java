package com.example.tests;

import com.aventstack.extentreports.Status;
import com.example.loginautomation.pages.LoginPage;
import com.example.tests.web.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void testValidLogin() {
        test = extent.createTest("Valid Login Test", "Tests if a user can login with valid credentials");

        driver.get("google.com");
        test.log(Status.INFO, "Navigated to login page");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("validUsername");
        test.log(Status.INFO, "Entered username");

        loginPage.enterPassword("validPassword");
        test.log(Status.INFO, "Entered password");

        loginPage.clickLoginButton();
        test.log(Status.INFO, "Clicked login button");

        // Add assertions to verify successful login
        Assert.assertTrue(driver.getTitle().contains("Dashboard"));
        test.log(Status.PASS, "Login test passed");
    }
}
