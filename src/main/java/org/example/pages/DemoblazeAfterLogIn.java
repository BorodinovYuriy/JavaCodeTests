package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoblazeAfterLogIn extends DemoblazeBeforeLogIn {
    String categoryAll;
    String cardBlock;

    public DemoblazeAfterLogIn(WebDriver chromeDriver) {
        super(chromeDriver);
        super.baseUrl = "https://www.demoblaze.com/index.html";
        this.categoryAll = "//div[@class='list-group']";
        this.cardBlock = "//div[@class ='card-block']";
    }

    public List<String> getCategoryListXpath() {
        List<String>xpathList = new ArrayList<>();
        WebElement categoriesDiv = waitAndGetWebElementFromXpath(categoryAll);
        List<WebElement> categoryLinks = categoriesDiv.findElements(
                By.xpath("./a[@id='itemc']"));
        for (WebElement link : categoryLinks) {
            xpathList.add(
                    "//a[@id='itemc' and text() ='"
                    +link.getText()
                    +"']");
        }
        xpathList.forEach(System.out::println);
        return xpathList;
    }


    public Map<String, String> addSomeItemToCart() {
        Map<String,String> addedItems = new HashMap<>();

        List<String> category = getCategoryListXpath();

       // WebElement allCardBlock = waitAndGetWebElementFromXpath();



        return null;
    }
}













