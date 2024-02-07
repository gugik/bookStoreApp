package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.utils.Wait;
import org.utils.WebTable;

public class BookStorePage extends MainPage {

    @FindBy(className = "ReactTable")
    private WebElement reactTable;
    @FindBy(id = "login")
    private WebElement loginButton;
    @FindBy(xpath = "//*[text()='Profile']")
    private WebElement profile;
    @FindBy(className = "ReactTable")
    private WebElement bookTable;

    public BookStorePage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(new Wait(driver).isElementPresent(reactTable, 5),
                "BookStorePage not loaded");
    }

    public LoginPage goToLoginPage() {
        loginButton.click();
        return new LoginPage(driver);
    }

    public BookStorePage goToUserProfile() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                profile);
        profile.click();
        return new BookStorePage(driver);
    }

    public int getBooksAmount() {
        WebTable table = new WebTable(bookTable);
        return table.getTableRows().size();
    }

    public BookDetailsPage getBookDetailsPage(int bookArrangeNumber) {
        WebTable table = new WebTable(bookTable);
        WebElement bookElement = table.getTableRows()
                .get(bookArrangeNumber).findElement(By.cssSelector("a[href*='/profile?book']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                bookElement);
        bookElement.click();
        return new BookDetailsPage(driver);
    }
}
