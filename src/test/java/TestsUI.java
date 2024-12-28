//Задание 2 - UI тесты selenium/selenide
//ссылка на UI - https://www.demoblaze.com/
//    В реализации необходимо использовать только xpath, паттерны Page object, Page factory
import org.example.pages.BaseDemoblaze;
import org.example.pages.DemoblazeAfterLogIn;
import org.example.pages.DemoblazeBeforeLogIn;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class TestsUI extends BaseUiTest {
    //Кейс 1:
    @Test
    public void registrationCheckPO() throws InterruptedException {
        BaseDemoblaze baseDemoblaze = new BaseDemoblaze(chromeDriver);
        DemoblazeBeforeLogIn demoblazeBeforeLogIn = new DemoblazeAfterLogIn(chromeDriver);
        //  1. Зарегистрироваться под любыми данными(использовать библиотеку для генерации тестовых данных)
        demoblazeBeforeLogIn.singUp();
        //  2. Залогиниться с этими данными
        demoblazeBeforeLogIn.logInAfterSingUp();
        //  3. Добавить в корзину по одному любому гаджету из каждой категории
        DemoblazeAfterLogIn demoblazeAfterLogIn = new DemoblazeAfterLogIn(chromeDriver);

        Map<String,String> addedItems = demoblazeAfterLogIn.addSomeItemToCart();









//        4. Сравнить цену из общего списка и с карточки товара
//        5. Перейти в корзину и убедиться, что общая цена посчитана верно
//        6. Оформить заказ
//        7. Убедиться что дата в конце заказа совпадает с датой в системе
//        В реализации необходимо использовать только xpath, паттерны Page object, Page
//        factory


        Thread.sleep(100000);
    }



    @Test
    public void registrationCheckPF(){

    }






























}
