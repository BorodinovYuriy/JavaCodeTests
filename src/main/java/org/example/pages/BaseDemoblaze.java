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
    protected By home;


    public BaseDemoblaze(WebDriver chromeDriver){
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, 30);
        this.faker = new Faker();
        this.baseUrl = "https://www.demoblaze.com/";
        this.home = By.xpath("//a[@href='index.html' and @class='nav-link']");
    }
    public BaseDemoblaze openPage(){
        chromeDriver.get(baseUrl);
        return this;
    }
    public WebElement waitAndGetWebElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public List<WebElement> getWebElements(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public WebElement getWebElement(By locator){
        return chromeDriver.findElement(locator);
    }
    public void clickElement(String xPath){
        waitAndGetWebElement(By.xpath(xPath)).click();
    }
    public void clickHome(){
        waitAndGetWebElement(home).click();
    }
    public String extractPrice(String priceText) {
        String regex = "\\$\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(priceText);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }
    public BaseDemoblaze goHomeAndWait(DemoblazeBeforeLogIn beforeLogIn) {
        clickHome();
        beforeLogIn.waitConfirmLogin();
        return this;
    }
}