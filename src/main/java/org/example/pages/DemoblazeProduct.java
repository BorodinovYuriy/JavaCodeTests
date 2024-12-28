package org.example.pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class DemoblazeProduct extends BaseDemoblaze {
    private final By addToCartButton;
    private final By productTitle;
    private final By productPrice;

    public DemoblazeProduct(WebDriver chromeDriver) {
        super(chromeDriver);
        this.addToCartButton = By.xpath("//a[text()='Add to cart']");
        this.productTitle = By.xpath( "//h2[@class='name']");
        this.productPrice = By.xpath("//h3[@class='price-container']");
    }

    public DemoblazeProduct addToCart() {
        waitAndGetWebElement(addToCartButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }

    public Map<String, String> getProductInfo() {
        WebElement titleElement = waitAndGetWebElement(productTitle);
        WebElement priceElement = waitAndGetWebElement(productPrice);

        String title = titleElement.getText();
        String price = extractPrice(priceElement.getText());

        Map<String, String> tmp = new HashMap<>();
        tmp.put(title, price);
        return tmp;
    }
}