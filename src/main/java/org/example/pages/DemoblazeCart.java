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
    private final By cartButton;
    private final By tableResponsive;
    private final By totalPrise;
    private final By priseListPath;
    private final By placeOrderButton;
    private final By purchaseButton;
    private final By sweetAlert;
    private final By dateField;
    private final By oK;
    private final By name;
    private final By country;
    private final By city;
    private final By card;
    private final By month;
    private final By year;


    public DemoblazeCart(WebDriver chromeDriver) {
        super(chromeDriver);
        this.cartButton = By.xpath("//a[@id='cartur']");
        this.tableResponsive = By.xpath("//div[@class='table-responsive']");
        this.totalPrise = By.xpath("//h3[@id='totalp']");
        this.priseListPath = By.xpath("//div[@class='table-responsive']//tbody//tr/td[3]");
        this.placeOrderButton = By.xpath("//button[@type='button' and text()='Place Order']");
        this.purchaseButton = By.xpath("//button[@onclick='purchaseOrder()']");
        this.sweetAlert = By.xpath("//div[@class='sweet-alert  showSweetAlert visible']");
        this.dateField = By.xpath("//p[@class='lead text-muted ']");
        this.oK = By.xpath("//button[text()='OK']");
        this.name = By.xpath("//input[@id='name']");
        this.country = By.xpath("//input[@id='country']");
        this.city = By.xpath("//input[@id='city']");
        this.card = By.xpath("//input[@id='card']");
        this.month = By.xpath("//input[@id='month']");
        this.year = By.xpath("//input[@id='year']");
    }

    private DemoblazeCart waitForAllPricesLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableResponsive));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(priseListPath));
        return this;
    }
    public DemoblazeCart loadCartPage() {
        getWebElement(cartButton).click();
        waitForAllPricesLoaded();
        return this;
    }
    public DemoblazeCart checkTotalPrise() {
        try {int expectedTotal = Integer.parseInt(waitAndGetWebElement(totalPrise).getText());
            List<WebElement> priceElements = getWebElements(priseListPath);
            int actualTotal = 0;

            for (WebElement priceElement : priceElements) {
                actualTotal += Integer.parseInt(priceElement.getText());
            }
            Assertions.assertEquals(expectedTotal, actualTotal, "Общая сумма товаров не совпадает с суммой всех товаров");
        } catch (NumberFormatException e) {
            Assertions.fail("NumberFormatException при сравнивании цен в корзине" + e.getMessage());
        }
        return this;
    }
    public DemoblazeCart makeOrder() {
        getWebElement(placeOrderButton).click();
        waitAndGetWebElement(name).sendKeys(faker.name().name());
        getWebElement(country).sendKeys(faker.address().country());
        getWebElement(city).sendKeys(faker.address().city());
        getWebElement(card).sendKeys(faker.number().digits(16));
        getWebElement(month).sendKeys(faker.number().digits(2));
        getWebElement(year).sendKeys(faker.number().digits(4));

        waitAndGetWebElement(purchaseButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sweetAlert));
        wait.until(ExpectedConditions.visibilityOfElementLocated(oK));
        // Получаем весь текст параграфа
        String paragraphText = waitAndGetWebElement(dateField).getText();
        String dateString = "";

        if(paragraphText.contains("Date:")){
            int startIndex = paragraphText.indexOf("Date:") + "Date:".length();
            dateString = paragraphText.substring(startIndex).trim();
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate orderDate = parseDateFromString(dateString);

        if (orderDate != null) {
            Assertions.assertEquals(currentDate, orderDate, "Даты не совпадают!");
        }
        return this;
    }
    public DemoblazeCart performOrder() {
        loadCartPage()
                .checkTotalPrise();
        makeOrder();
        return this;
    }
    private LocalDate parseDateFromString(String dateString) {
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dateString, dateFormatter);
        } catch (DateTimeParseException e) {
            Assertions.fail("Ошибка парсинга даты: " + dateString + " " + e.getMessage());
            return null;
        }
    }
}
