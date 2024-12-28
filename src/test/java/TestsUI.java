import org.example.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Map;


public class TestsUI extends BaseUiTest {

    @Test
    public void registrationCheckPO() {
        // Создание экземпляров Page Object классов.
        BaseDemoblaze baseDemoblaze = new BaseDemoblaze(chromeDriver);
        DemoblazeBeforeLogIn beforeLogIn = new DemoblazeBeforeLogIn(chromeDriver);
        DemoblazeAfterLogIn afterLogIn = new DemoblazeAfterLogIn(chromeDriver);
        DemoblazeProduct product = new DemoblazeProduct(chromeDriver);
        DemoblazeCart cart = new DemoblazeCart(chromeDriver);

        //  1. Зарегистрироваться под любыми данными(использовать библиотеку для генерации тестовых данных)
        beforeLogIn.openPage()
                .singUp()
                //  2. Залогиниться с этими данными
                .logInAfterSingUp();
        //  3. Добавить в корзину по одному любому гаджету из каждой категории и
        //  4. Сравнить цену из общего списка и с карточки товара
        afterLogIn.getCategoryListXpath().forEach(categoryXpath -> {
            baseDemoblaze.clickElement(categoryXpath);
            // Получаем информацию о первом товаре из категории
            Map<String, String> mainPrice = afterLogIn.getMainProductPrice();
            String mainPriceValue = mainPrice.values().iterator().next();
            // Переходим на страницу товара и получаем цену оттуда
            afterLogIn.openFirstProductPage();
            Map<String,String> itemPrice = product.getProductInfo();
            String itemPriceValue = itemPrice.values().iterator().next();

            //  4. Сравниваем цены
            Assertions.assertEquals(mainPriceValue,itemPriceValue, "Цена не совпадает");
            // Добавляем продукт в корзину
            product.addToCart();
            baseDemoblaze.clickHome();
            beforeLogIn.waitConfirmLogin();
        });

        //   5. Перейти в корзину и убедиться, что общая цена посчитана верно
        cart.loadCartPage()
                .checkTotalPrise();
        //   6. Оформить заказ и
        //   7. Убедиться что дата в конце заказа совпадает с датой в системе
        cart.makeOrder();
    }
}