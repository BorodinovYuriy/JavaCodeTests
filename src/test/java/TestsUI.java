//Задание 2 - UI тесты selenium/selenide
//ссылка на UI - https://www.demoblaze.com/
//    В реализации необходимо использовать только xpath, паттерны Page object, Page factory
import org.example.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsUI extends BaseUiTest {
    //Кейс 1:
    @Test
    public void registrationCheckPO() throws InterruptedException {
        BaseDemoblaze baseDemoblaze = new BaseDemoblaze(chromeDriver);
        DemoblazeBeforeLogIn beforeLogIn = new DemoblazeBeforeLogIn(chromeDriver);
        DemoblazeAfterLogIn afterLogIn = new DemoblazeAfterLogIn(chromeDriver);
        DemoblazeProduct product = new DemoblazeProduct(chromeDriver);
        DemoblazeCart cart = new DemoblazeCart(chromeDriver);

        //  1. Зарегистрироваться под любыми данными(использовать библиотеку для генерации тестовых данных)
        beforeLogIn.singUp();
        //  2. Залогиниться с этими данными
        beforeLogIn.logInAfterSingUp();
        //  3. Добавить в корзину по одному любому гаджету из каждой категории


        List<String> category = afterLogIn.getCategoryListXpath();
        for (int i = 0; i < category.size(); i++) {
            Map<String,String> mainPrise = new HashMap<>();
            Map<String,String> itemPrise = new HashMap<>();

            baseDemoblaze.getWebElementFromXpath(category.get(i)).click();
            System.out.println("Переход---"+category.get(i));
            //Не к чему привязаться (даже с замедлением сети) поэтому так
            Thread.sleep(1000);
            //Выбор первых (реализация без Random) блоков
            WebElement titleElement = baseDemoblaze
                    .waitAndGetWebElementFromXpath("//div[@class ='card-block'][1]//h4/a");
            WebElement priceElement = baseDemoblaze
                    .waitAndGetWebElementFromXpath("//div[@class ='card-block'][1]//h5");
            String title = titleElement.getText();
            String price = priceElement.getText();
            mainPrise.put(title, baseDemoblaze.extractPrice(price));
            // переходим в карточку
            titleElement.click();
            Map<String,String> prodPrise = product.getInfo();
            prodPrise.forEach(itemPrise::putIfAbsent);
        //  4. Сравнить цену из общего списка и с карточки товара
            String expectedValue = mainPrise.values().iterator().next();
            String actualValue = itemPrise.values().iterator().next();
            // Сравниваем значения
            Assertions.assertEquals(expectedValue, actualValue, "Значения не совпадают");

            System.out.println(mainPrise);
            System.out.println(itemPrise);
            //Добавление продукта в корзину
            product.addToCart();
            System.out.println("----");
            baseDemoblaze.clickHome();
            beforeLogIn.waitConfirmLogin();
        }
        //   5. Перейти в корзину и убедиться, что общая цена посчитана верно
            cart.loadCartPage();
            cart.checkTotalPrise();
        //   6. Оформить заказ
            cart.makeOrder();

        //   7. Убедиться что дата в конце заказа совпадает с датой в системе









//        В реализации необходимо использовать только xpath, паттерны Page object, Page
//        factory


        Thread.sleep(1000);
    }































}
