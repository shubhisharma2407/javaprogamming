package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(className = "inventory_item_name")
    private WebElement productInCartName;

    @FindBy(className = "inventory_item_price")
    private WebElement productInCartPrice;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductInCartName() {
        return getText(productInCartName);
    }

    public String getProductInCartPrice() {
        return getText(productInCartPrice);
    }
}
