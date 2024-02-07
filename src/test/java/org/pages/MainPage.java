package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.utils.Wait;

public class MainPage {

    protected WebDriver driver;
    @FindBy(id = "app")
    private WebElement mainElement;
    @FindBy(className = "fc-primary-button")
    private WebElement consentButton;
    @FindBy(xpath = "//*[text()='Book Store Application']")
    private WebElement bookApp;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Assert.assertTrue(new Wait(driver).isElementPresent(mainElement, 5),
                "MainPage not loaded");
    }

    public BookStorePage goToBookStoreApp() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                bookApp);
        bookApp.click();
        return new BookStorePage(driver);
    }

    public MainPage clickOnConsentButton() {
        consentButton.click();
        return this;
    }

}
