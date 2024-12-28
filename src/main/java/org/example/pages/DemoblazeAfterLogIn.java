package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoblazeAfterLogIn extends BaseDemoblaze {
    private final By categoryAll;
    private final By allCardBlocks;
    private final By firstProductLink;
    private final By firstProductPrice;

    public DemoblazeAfterLogIn(WebDriver chromeDriver) {
        super(chromeDriver);
        this.categoryAll = By.xpath("//div[@class='list-group']");
        this.allCardBlocks = By.xpath("//div[@class ='card-block']");
        this.firstProductLink = By.xpath("//div[@class ='card-block'][1]//h4/a");
        this.firstProductPrice = By.xpath("//div[@class ='card-block'][1]//h5");
    }

    public List<String> getCategoryListXpath() {
        return  getWebElements(categoryAll)
                .stream()
                .map(element -> element.findElements(By.xpath("./a[@id='itemc']")))
                .flatMap(List::stream)
                .map(webElement -> "//a[@id='itemc' and text() ='" + webElement.getText() + "']")
                .collect(Collectors.toList());
    }


    public Map<String,String> getMainProductPrice() {
        String title = waitAndGetWebElement(firstProductLink).getText();
        String price = extractPrice(waitAndGetWebElement(firstProductPrice).getText());
        Map<String,String> itemInfo = new HashMap<>();
        itemInfo.put(title,price);
        return itemInfo;
    }
    public void openFirstProductPage() {
        waitAndGetWebElement(firstProductLink).click();
    }

}