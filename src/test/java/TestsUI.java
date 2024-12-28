import org.example.pages.*;
import org.junit.jupiter.api.Test;

public class TestsUI extends BaseUiTest {
    @Test
    public void registrationCheckPO() {
        // Создание экземпляров Page Object классов.
        BaseDemoblaze baseDemoblaze = new BaseDemoblaze(chromeDriver);
        DemoblazeBeforeLogIn beforeLogIn = new DemoblazeBeforeLogIn(chromeDriver);
        DemoblazeAfterLogIn afterLogIn = new DemoblazeAfterLogIn(chromeDriver);
        DemoblazeProduct product = new DemoblazeProduct(chromeDriver);
        DemoblazeCart cart = new DemoblazeCart(chromeDriver);

        // 1. Регистрация и логин
        beforeLogIn.performRegistrationAndLogin();

        // 2. Добавление товаров и проверка цен
        afterLogIn.getCategoryListXpath().forEach(categoryXpath ->
                afterLogIn.addProductAndValidatePrice(product, categoryXpath)
                        .goHomeAndWait(beforeLogIn)
        );

        // 3. Оформление заказа
        cart.performOrder();
    }
}