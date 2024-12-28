package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoblazeAfterLogIn extends BaseDemoblaze {
    String categoryAll;
    String allCardBlocks;

    public DemoblazeAfterLogIn(WebDriver chromeDriver) {
        super(chromeDriver);
        super.baseUrl = "https://www.demoblaze.com/index.html";
        this.categoryAll = "//div[@class='list-group']";
        this.allCardBlocks = "//div[@class ='card-block']";

    }
//-------------------------------------------------------------------

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


    public Map<String,String>  addSomeItemToCart() {
        Map<String,String> addedItems = new HashMap<>();

        List<String> category = getCategoryListXpath();
        for(String xpath : category){
            getWebElementFromXpath(xpath).click();
            List<String> blocks = new ArrayList<>();
            WebElement blocksDiv = waitAndGetWebElementFromXpath(allCardBlocks);
            System.out.println(blocksDiv);

        }
        return null;
    }

    public Map<String,String>  printSomeItem() {
        Map<String,String> addedItems = new HashMap<>();

        List<String> category = getCategoryListXpath();
        for(String xpath : category){
            getWebElementFromXpath(xpath).click();
            List<WebElement> blocks = waitAndGetWebElementFromXpath(allCardBlocks)
                    .findElements(By.xpath("./*"));

            for (WebElement block : blocks) {
                WebElement titleElement = waitAndGetWebElementFromXpath(".//h4/a");
                WebElement priceElement = waitAndGetWebElementFromXpath(".//h5");

                String title = titleElement.getText();
                String price = priceElement.getText();
                addedItems.put(title, price);
            }
        }
        addedItems.forEach((key, value) -> System.out.println("Item: " + key + ", Price: " + value));

        return addedItems;
    }
















}













