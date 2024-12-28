package org.example.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DemoblazeCart extends BaseDemoblaze {
    String cartButton;
    String responsiveDiv;
    String totalPrise;
    String tableResponsive;
    String priseListPath;
    String placeOrderButton;
    String purchaseButton;
    String sweetAlert;

    String name;
    String country;
    String city;
    String card;
    String month;
    String year;
    // Убираем /text()[contains(.,'Date:')] из xpath
    String dateField;
    String oK;

    public DemoblazeCart(WebDriver chromeDriver) {
        super(chromeDriver);
        this.cartButton = "//a[@id='cartur']";
        this.responsiveDiv = "//div[@class='table-responsive']";
        this.totalPrise = "//h3[@id='totalp']";
        this.tableResponsive = "//div[@class='table-responsive']";
        this.priseListPath = "//div[@class='table-responsive']//tbody//tr/td[3]";
        this.placeOrderButton = "//button[@type='button' and text()='Place Order']";
        this.purchaseButton = "//button[@onclick='purchaseOrder()']";
        this.sweetAlert = "//div[@class='sweet-alert  showSweetAlert visible']";
        // Исправлено:  убираем /text()[contains(.,'Date:')]
        this.dateField = "//p[@class='lead text-muted ']";

        this.name = "//input[@id='name']";
        this.country = "//input[@id='country']";
        this.city = "//input[@id='city']";
        this.card = "//input[@id='card']";
        this.month = "//input[@id='month']";
        this.year = "//input[@id='year']";

        this.oK = "//button[text()='OK']";
    }

    private void waitForAllPricesLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableResponsive)));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(priseListPath)));
    }

    public void loadCartPage() {
        getWebElementFromXpath(cartButton).click();
        waitForAllPricesLoaded();
    }

    public void checkTotalPrise() {
        try {
            WebElement totalElement = getWebElementFromXpath(totalPrise);
            int expectedTotal = Integer.parseInt(totalElement.getText());
            List<WebElement> priceElements = getWebElementsFromXpath(priseListPath);
            int actualTotal = 0;

            for (WebElement priceElement : priceElements) {
                actualTotal += Integer.parseInt(priceElement.getText());
            }
            Assertions.assertEquals(expectedTotal, actualTotal, "Общая сумма товаров не совпадает с суммой всех товаров");
        } catch (NumberFormatException e) {
            Assertions.fail("NumberFormatException при сравнивании цен в корзине" + e.getMessage());
        }
    }

    public void makeOrder() {
        getWebElementFromXpath(placeOrderButton).click();
        waitAndGetWebElementFromXpath(name).sendKeys(faker.name().name());
        getWebElementFromXpath(country).sendKeys(faker.address().country());
        getWebElementFromXpath(city).sendKeys(faker.address().city());
        getWebElementFromXpath(card).sendKeys(faker.number().digits(16));
        getWebElementFromXpath(month).sendKeys(faker.number().digits(2));
        getWebElementFromXpath(year).sendKeys(faker.number().digits(4));

        waitAndGetWebElementFromXpath(purchaseButton).click();
        //1. ждем пока модалка станет видимой
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sweetAlert)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oK)));

        // Получаем элемент <p>
        WebElement paragraphElement = getWebElementFromXpath(dateField);
        // Получаем текст всего параграфа
        String paragraphText = paragraphElement.getText();
        String dateString = "";

        if(paragraphText.contains("Date:")){
            int startIndex = paragraphText.indexOf("Date:") + "Date:".length();
            dateString = paragraphText.substring(startIndex).trim();
        }


        System.out.println("Date: " + dateString);
        // 4. Сравниваем даты, если парсинг прошел успешно
        LocalDate currentDate = LocalDate.now();
        LocalDate orderDate = parseDateFromString(dateString);
        if (orderDate != null) {
            Assertions.assertEquals(currentDate, orderDate, "Даты не совпадают!");
        }
    }

    private LocalDate parseDateFromString(String dateString) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            Assertions.fail("Ошибка парсинга даты: " + dateString + " " + e.getMessage());
            return null; // or throw an exception
        }
    }


}