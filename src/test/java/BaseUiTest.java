import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseUiTest {
    protected WebDriver chromeDriver;
    protected Faker faker;


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));

        chromeDriver = new ChromeDriver();
        faker = new Faker();

        chromeDriver.manage().window().maximize();

        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

    }

    @AfterEach
    public void after() {
        chromeDriver.quit();
    }
}