package org.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.api.Book;
import org.api.Isbn;
import org.pages.BookDetailsPage;
import org.pages.BookStorePage;
import org.pages.MainPage;

import java.util.ArrayList;
import java.util.List;

public class Test extends BaseTest {

    @org.testng.annotations.Test
    public void apiTest() {
        String userName = RandomStringUtils.randomAlphabetic(8);
        String password = RandomStringUtils.randomAlphanumeric(10) + "%^";

        //Create a user
        String userId = bookStoreService.createUser(userName, password);

        //Generate Authentication Token
        String token = bookStoreService.generateToken(userName, password);

        //Get List of Books
        List<Book> books = bookStoreService.getBooks();
        softAssert.assertNotNull(books, "At least one book must exist");

        //Filter by Publisher or Author
        //get from source
        String authorName = "Addy Osmani";
        List<Book> bookListByAuthor = bookStoreService.getBooksByAuthor(authorName);
        bookListByAuthor.forEach(book ->
                softAssert.assertEquals(book.getAuthor(), authorName, "Author"));
        String publisher = "No Starch Press";
        List<Book> bookListByPublisher = bookStoreService.getBooksByPublisher(publisher);
        bookListByPublisher.forEach(book ->
                softAssert.assertEquals(book.getPublisher(), publisher, "Publisher"));

        //or reuse previous step
        bookStoreService.getBooksByAuthor(authorName, books).forEach(book ->
                softAssert.assertEquals(book.getAuthor(), authorName, "Author"));
        bookStoreService.getBooksByPublisher(publisher, books).forEach(book ->
                softAssert.assertEquals(book.getPublisher(), publisher, "Publisher"));

        //Post Books to the User in Context
        List<Isbn> isbns = new ArrayList<>();
        bookListByAuthor.forEach(book -> isbns.add(new Isbn(book.getIsbn())));
        bookListByPublisher.forEach(book -> isbns.add(new Isbn(book.getIsbn())));
        List<Isbn> isbnsAddedBooks = bookStoreService.postBooks(userId, isbns, token);
        softAssert.assertNotNull(isbnsAddedBooks, "IsbnsAddedBooks list should not be empty");
        softAssert.assertEquals(isbnsAddedBooks.size(), isbns.size(), "Count of lists should be equal");

        //Test the API & Validate Performed Actions
        List<Book> userBooks = bookStoreService.getUserBooks(userId, token);
        softAssert.assertNotNull(userBooks, "UserBooks list should not be empty");
        softAssert.assertEquals(userBooks.size(), isbns.size(), "Count of lists should be equal");
        userBooks.forEach(book ->
                softAssert.assertEquals(isbns.stream().filter(
                        isbn -> isbn.getIsbn().equals(book.getIsbn())).count(), 1, "UserBooks isbns should be present")
        );

        //Part two

        //Navigate to the DemoQA Website
        mainPage = new MainPage(driver);
        mainPage.clickOnConsentButton();

        //Access the Bookstore Application
        BookStorePage bookStorePage = mainPage.goToBookStoreApp();

        //Login with User in Context
        bookStorePage.goToLoginPage().loginUser(userName, password);

        //Verify Number of Books Added to User
        bookStorePage.goToUserProfile();
        int booksAmount = bookStorePage.getBooksAmount();
        softAssert.assertEquals(booksAmount, userBooks.size(), "Count of lists should be equal");

        //Verify Book Details
        List<Book> userBooksUi = new ArrayList<>();
        for (int i = 0; i < booksAmount; i++) {
            BookDetailsPage bookDetailsPage = bookStorePage.getBookDetailsPage(i);
            userBooksUi.add(bookDetailsPage.getBookDetails());
            bookStorePage = bookDetailsPage.returnToStore();
        }
        softAssert.assertEquals(userBooksUi.size(), userBooks.size(), "Count of lists should be equal");
        for (int i = 0; i < booksAmount; i++) {
            verifyBookDetails(userBooksUi.get(i), userBooksUi);
        }
        softAssert.assertAll();
    }

}