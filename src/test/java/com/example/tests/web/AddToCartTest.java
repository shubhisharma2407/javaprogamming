package com.example.tests.web;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.example.pages.CartPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import java.io.*;
import java.time.Duration;
import java.util.Properties;


public class AddToCartTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    ExtentReports extent;
    ExtentTest test;

    Properties properties = new Properties();

    @BeforeMethod
    public void setup() throws IOException {

        FileInputStream fileInput = new FileInputStream("config.properties");
        properties.load(fileInput);
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Get URL from properties file
        String url = properties.getProperty("base.url");
        driver.get(url);

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

        // ExtentReports setup
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);


        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);

         test = extent.createTest("Add to Cart Test", "Testing the add-to-cart functionality");
    }
    

    @Test
    public void testAddToCart() throws IOException {

        test.log(Status.INFO, "Logging in to the application");
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getTitle().contains("Swag Labs"));
        test.log(Status.PASS, "Successfully logged in and landed on Products page");


        // Get product details
        String productName = productsPage.getFirstProductName();
        String productPrice = productsPage.getFirstProductPrice();

        // Store in text file
        BufferedWriter writer = new BufferedWriter(new FileWriter("productDetails.txt"));
        writer.write("Product Name: " + productName + "\n");
        writer.write("Product Price: " + productPrice);
        writer.close();
        test.log(Status.INFO, "Product details saved to file");

        // Add product to cart and navigate to cart
        productsPage.checkWaitForProduct();
        productsPage.addFirstProductToCart();
        productsPage.goToCart();

        // Verify product in cart
        Assert.assertEquals(cartPage.getProductInCartName(), productName);
        Assert.assertEquals(cartPage.getProductInCartPrice(), productPrice);
        test.log(Status.PASS, "Product successfully added to cart and verified");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
        test.log(Status.INFO, "Browser closed");
        // Flush ExtentReports
        extent.flush();
    }
}
