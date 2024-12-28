package org.example.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DemoblazeBeforeLogIn extends BaseDemoblaze{

    protected String username;
    protected String password;

    protected String singUpButton;
    protected String singUpUsernameField;
    protected String singUpPasswordField;
    protected String singUpConfirmButton;
    protected String loginButton;
    protected String loginUsername;
    protected String loginPassword;
    protected String loginConfirmButton;
    protected String confirmLogin;

    public DemoblazeBeforeLogIn(WebDriver chromeDriver){
        super(chromeDriver);
        this.username = faker.name().username();
        this.password = faker.internet().password();
        this.singUpButton = "//a[@id='signin2']/ancestor::li[@class='nav-item']";
        this.singUpUsernameField = "//input[@type='text' and @id='sign-username']";
        this.singUpPasswordField = "//input[@type='password' and @id='sign-password']";
        this.singUpConfirmButton ="//button[text()='Sign up' and @onclick='register()']";
        this.loginButton ="//a[@id='login2']/ancestor::li[@class='nav-item']";
        this.loginUsername ="//input[@type='text' and @id='loginusername']";
        this.loginPassword  = "//input[@type='password' and @id='loginpassword']";
        this.loginConfirmButton ="//button[text()='Log in' and @onclick='logIn()']";
        this.confirmLogin = "//a[@id='nameofuser']";
    }


    public void singUp(){
        chromeDriver.get(baseUrl);
        waitAndGetWebElementFromXpath(singUpButton).click();
        waitAndGetWebElementFromXpath(singUpUsernameField).sendKeys(username);
        getWebElementFromXpath(singUpPasswordField).sendKeys(password);
        getWebElementFromXpath(singUpConfirmButton).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public void logInAfterSingUp(){
        waitAndGetWebElementFromXpath(loginButton).click();
        waitAndGetWebElementFromXpath(loginUsername).sendKeys(username);
        getWebElementFromXpath(loginPassword).sendKeys(password);
        getWebElementFromXpath(loginConfirmButton).click();
        //Warn!  StaleElementReferenceException
        waitConfirmLogin();
    }

    public WebElement waitAndGetWebElementFromXpath(String xPath) {
        return super.waitAndGetWebElementFromXpath(xPath);
    }
    public void waitConfirmLogin(){
        waitAndGetWebElementFromXpath(confirmLogin);
    }
}
