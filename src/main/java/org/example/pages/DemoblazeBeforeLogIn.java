package org.example.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class DemoblazeBeforeLogIn extends BaseDemoblaze {
    private String username;
    private String password;
    private final By singUpButton;
    private final By singUpUsernameField;
    private final By singUpPasswordField;
    private final By singUpConfirmButton;
    private final By loginButton;
    private final By loginUsername;
    private final By loginPassword;
    private final By loginConfirmButton;
    private final By confirmLogin;

    public DemoblazeBeforeLogIn(WebDriver chromeDriver) {
        super(chromeDriver);
        this.username = faker.name().username();
        this.password = faker.internet().password();
        this.singUpButton = By.xpath("//a[@id='signin2']/ancestor::li[@class='nav-item']");
        this.singUpUsernameField = By.xpath("//input[@type='text' and @id='sign-username']");
        this.singUpPasswordField = By.xpath("//input[@type='password' and @id='sign-password']");
        this.singUpConfirmButton = By.xpath("//button[text()='Sign up' and @onclick='register()']");
        this.loginButton = By.xpath("//a[@id='login2']/ancestor::li[@class='nav-item']");
        this.loginUsername = By.xpath("//input[@type='text' and @id='loginusername']");
        this.loginPassword = By.xpath("//input[@type='password' and @id='loginpassword']");
        this.loginConfirmButton = By.xpath("//button[text()='Log in' and @onclick='logIn()']");
        this.confirmLogin = By.xpath("//a[@id='nameofuser']");
    }

    public DemoblazeBeforeLogIn openPage() {
        super.openPage();
        return this;
    }
    public DemoblazeBeforeLogIn singUp(){
        waitAndGetWebElement(singUpButton).click();
        waitAndGetWebElement(singUpUsernameField).sendKeys(username);
        getWebElement(singUpPasswordField).sendKeys(password);
        getWebElement(singUpConfirmButton).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
        return this;
    }
    public DemoblazeBeforeLogIn logInAfterSingUp(){
        waitAndGetWebElement(loginButton).click();
        waitAndGetWebElement(loginUsername).sendKeys(username);
        getWebElement(loginPassword).sendKeys(password);
        getWebElement(loginConfirmButton).click();
        waitConfirmLogin();
        return this;
    }
    public DemoblazeBeforeLogIn waitConfirmLogin(){
        waitAndGetWebElement(confirmLogin);
        return this;
    }
}