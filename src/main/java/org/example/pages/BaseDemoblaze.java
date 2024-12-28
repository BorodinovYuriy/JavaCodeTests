package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class BaseDemoblaze {

   protected WebDriver chromeDriver;
   protected WebDriverWait wait;

    protected String baseUrl;

    public BaseDemoblaze(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 30);
        this.baseUrl = "https://www.demoblaze.com/";
    }

    public WebElement waitAndGetWebElementFromXpath(String xPath){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
    public WebElement getWebElementFromXpath(String xPath){
        return chromeDriver.findElement(By.xpath(xPath));
    }


}
