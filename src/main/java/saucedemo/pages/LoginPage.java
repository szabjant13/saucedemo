package saucedemo.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    // Page elements will come here
    @FindBy(xpath = "//input[@id='user-name']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[.='Epic sadface: Sorry, this user has been locked out.']")
    private WebElement lockedOutErrorMessage;

    @FindBy(xpath = "//h3[.='Epic sadface: Username and password do not match any user in this service']")
    private WebElement wrongCredentialsErrorMessage;


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLockedOutErrorMessageDisplayed() {
        return lockedOutErrorMessage.isDisplayed();
    }

    public boolean isWrongCredentialsErrorMessageDisplayed() {
        return wrongCredentialsErrorMessage.isDisplayed();
    }

    public void login(String username, String password) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            return;
        } else {
            throw new IllegalStateException("Login failed: Unexpected URL " + driver.getCurrentUrl());
        }
    }

    public void wrongCredentialsLogin(String username, String password) throws InterruptedException{
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        if(isWrongCredentialsErrorMessageDisplayed() ){
            return;
        } else{
            throw new NoSuchElementException("Error message is not visible after failed login.");
        }
    }

    public void lockedUserLogin(String username, String password) throws InterruptedException{
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        if(isLockedOutErrorMessageDisplayed() ){
            return;
        } else{
            throw new NoSuchElementException("Error message is not visible after failed login.");
        }
    }
}
