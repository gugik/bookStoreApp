package org.pages;

import org.api.Book;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.utils.Wait;

public class BookDetailsPage extends MainPage {

    @FindBy(id = "ISBN-wrapper")
    private WebElement isbn;
    @FindBy(id = "title-wrapper")
    private WebElement title;
    @FindBy(id = "subtitle-wrapper")
    private WebElement subtitle;
    @FindBy(id = "author-wrapper")
    private WebElement author;
    @FindBy(id = "publisher-wrapper")
    private WebElement publisher;
    @FindBy(id = "pages-wrapper")
    private WebElement pages;
    @FindBy(id = "description-wrapper")
    private WebElement description;
    @FindBy(id = "website-wrapper")
    private WebElement website;
    @FindBy(id = "addNewRecordButton")
    private WebElement returnButton;

    public BookDetailsPage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(new Wait(driver).isElementPresent(isbn, 5),
                "BookDetailPage not loaded");
    }

    public String getIsbn() {
        return isbn.findElement(By.id("userName-value")).getText().trim();
    }

    public String getTitle() {
        return title.findElement(By.id("userName-value")).getText().trim();
    }

    public String getSubtitle() {
        return subtitle.findElement(By.id("userName-value")).getText().trim();
    }

    public String getAuthor() {
        return author.findElement(By.id("userName-value")).getText().trim();
    }

    public String getPublisher() {
        return publisher.findElement(By.id("userName-value")).getText().trim();
    }

    public Integer getPages() {
        return Integer.parseInt(pages.findElement(By.id("userName-value")).getText().trim());
    }

    public String getDescription() {
        return description.findElement(By.id("userName-value")).getText().trim();
    }

    public String getWebsite() {
        return website.findElement(By.id("userName-value")).getText().trim();
    }

    public Book getBookDetails() {
        return new Book(getIsbn(), getTitle(), getSubtitle(),
                getAuthor(), getPublisher(), getPages(),
                getDescription(), getWebsite());
    }

    public BookStorePage returnToStore() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                returnButton);
        returnButton.click();
        return new BookStorePage(driver);
    }
}
