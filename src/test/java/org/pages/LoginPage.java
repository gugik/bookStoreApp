package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.utils.Wait;

public class LoginPage extends MainPage {

    @FindBy(id = "userName-wrapper")
    private WebElement mainElement;
    @FindBy(id = "userName")
    private WebElement userNameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(new Wait(driver).isElementPresent(mainElement, 5),
                "LoginPage not loaded");
    }

    public BookStorePage loginUser(String userName, String password) {
        userNameField.click();
        userNameField.clear();
        userNameField.sendKeys(userName);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
        return new BookStorePage(driver);
    }
}
