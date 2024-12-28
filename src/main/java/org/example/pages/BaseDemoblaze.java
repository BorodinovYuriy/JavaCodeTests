package org.example.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseDemoblaze {

   protected WebDriver chromeDriver;
   protected WebDriverWait wait;
   protected Faker faker;

    protected String baseUrl;
    protected String home;


    public BaseDemoblaze(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 30);
        this.faker = new Faker();
        this.baseUrl = "https://www.demoblaze.com/";
        this.home = "//a[@href='index.html' and @class='nav-link']";
    }

    public WebElement waitAndGetWebElementFromXpath(String xPath){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
    public List<WebElement> getWebElementsFromXpath(String xpath) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }
    public WebElement getWebElementFromXpath(String xPath){
        return chromeDriver.findElement(By.xpath(xPath));
    }
    public void clickHome(){
        waitAndGetWebElementFromXpath(home).click();

    }
    public String extractPrice(String priceText) {
        // Регулярное выражение: символ доллара, за которым следует одна или более цифр
        String regex = "\\$\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(priceText);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }


}
