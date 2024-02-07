package org.test;

import io.restassured.RestAssured;
import org.api.Book;
import org.api.BookStoreApi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.MainPage;
import org.service.BookStoreService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class BaseTest {

    //TODO Logging

    protected BookStoreService bookStoreService;
    protected SoftAssert softAssert;
    protected static final String mainUrl = "https://demoqa.com/";
    protected static WebDriver driver;
    protected static MainPage mainPage;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://demoqa.com/";
        this.bookStoreService = new BookStoreService(new BookStoreApi());
        this.softAssert = new SoftAssert();

        //setup for UI part
        System.setProperty("webdriver.chrome.driver", "C://chromedriver-win64//chromedriver-win64//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(mainUrl);
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }

    protected void verifyBookDetails(Book actualBook, List<Book> userBooksUi) {
        List<Book> expectedBookList = userBooksUi.stream().filter(arg -> arg.getTitle().equals(actualBook.getTitle()))
                .collect(Collectors.toList());
        softAssert.assertEquals(expectedBookList.size(), 1, "Only one actualBook should be present");
        Book expectedBook = expectedBookList.get(0);
        softAssert.assertEquals(actualBook.getIsbn(), expectedBook.getIsbn(), "Isbn");
        softAssert.assertEquals(actualBook.getTitle(), expectedBook.getTitle(), "Isbn");
        softAssert.assertEquals(actualBook.getSubTitle(), expectedBook.getSubTitle(), "SubTitle");
        softAssert.assertEquals(actualBook.getAuthor(), expectedBook.getAuthor(), "Author");
        softAssert.assertEquals(actualBook.getPublisher(), expectedBook.getPublisher(), "Publisher");
        softAssert.assertEquals(actualBook.getPages(), expectedBook.getPages(), "Pages");
        softAssert.assertEquals(actualBook.getDescription(), expectedBook.getDescription(), "Description");
        softAssert.assertEquals(actualBook.getWebsite(), expectedBook.getWebsite(), "Website");
    }
}
