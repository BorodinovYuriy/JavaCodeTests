package org.example.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

public class DemoblazeProduct extends BaseDemoblaze{
    String addToCartButton;
    String prodInfo;

    public DemoblazeProduct(WebDriver chromeDriver){
        super(chromeDriver);
        this.addToCartButton = "//a[text()='Add to cart']";
        this.prodInfo = "//div[@id='tbodyid']";
    }


    public void  addToCart() {
        waitAndGetWebElementFromXpath(addToCartButton).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public Map<String, String> getInfo() {
        Map<String, String> tmp = new HashMap<>();
        WebElement titleElement = waitAndGetWebElementFromXpath(prodInfo+"/h2[@class='name']");
        WebElement priceElement = waitAndGetWebElementFromXpath(prodInfo+"/h3[@class='price-container']");

        String title = titleElement.getText();
        String price = extractPrice(priceElement.getText());

        tmp.put(title,price);
        System.out.println("getInfo():");
        System.out.println(tmp);
        return tmp;
    }
}
