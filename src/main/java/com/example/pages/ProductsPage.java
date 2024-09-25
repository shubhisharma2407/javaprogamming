package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage extends BasePage {

    @FindBy(className = "inventory_item_name")
    private WebElement firstProductName;

    @FindBy(className = "inventory_item_price")
    private WebElement firstProductPrice;

    @FindBy(xpath = "//button[text()='Add to cart']")
    private WebElement addToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartButton;

    @FindBy(xpath =  "//span[contains(text(),'Products')]")
    private WebElement productsLabel;



    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstProductName() {
        return getText(firstProductName);
    }

    public String getFirstProductPrice() {
        return getText(firstProductPrice);
    }

    public void addFirstProductToCart() {
        click(addToCartButton);
    }

    public void goToCart() {
        click(cartButton);
    }

    public void checkWaitForProduct(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(productsLabel));
    }
}
