package org.service;

import org.api.Book;
import org.api.BookStoreApi;
import org.api.Isbn;
import org.api.User;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

public class BookStoreService {

    private BookStoreApi bookStoreApi;

    public BookStoreService(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    /**
     * Create user
     *
     * @param userName - user name
     * @param password - password
     * @return String
     */
    public String createUser(String userName, String password) {
        return bookStoreApi.createUser(userName, password);
    }

    /**
     * Generate token
     *
     * @param userName - user name
     * @param password - password
     * @return String
     */
    public String generateToken(String userName, String password) {
        return bookStoreApi.generateToken(userName, password);
    }

    /**
     * Get all books
     *
     * @return {@link List<Book>}
     */
    public List<Book> getBooks() {
        return bookStoreApi.getBooks();
    }


    /**
     * Filter books by publisher
     *
     * @param author - author
     * @return {@link List<Book>}
     */
    public List<Book> getBooksByAuthor(String author) {
        List<Book> booksByAuthor = getBooks().stream().filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
        Assert.assertNotNull(booksByAuthor, "At least one book must exist");
        return booksByAuthor;
    }


    /**
     * Get all books
     *
     * @param publisher - publisher name
     * @return {@link List<Book>}
     */
    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> booksByPublisher = getBooks().stream().filter(book -> book.getPublisher().equals(publisher))
                .collect(Collectors.toList());
        Assert.assertNotNull(booksByPublisher, "At least one book must exist");
        return booksByPublisher;
    }

    /**
     * Filter books by author using existed list of books
     *
     * @param author - author
     * @param books  - list of books
     * @return {@link List<Book>}
     */
    public List<Book> getBooksByAuthor(String author, List<Book> books) {
        List<Book> booksByAuthor = books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
        Assert.assertNotNull(booksByAuthor, "At least one book must exist");
        return booksByAuthor;
    }

    /**
     * Filter books by publisher using existed list of books
     *
     * @param publisher - publisher name
     * @param books     -list of books
     * @return {@link List<Book>}
     */
    public List<Book> getBooksByPublisher(String publisher, List<Book> books) {
        List<Book> booksByPublisher = books.stream().filter(book -> book.getPublisher().equals(publisher)).collect(Collectors.toList());
        Assert.assertNotNull(booksByPublisher, "At least one book must exist");
        return booksByPublisher;
    }

    /**
     * Create list of books for user
     *
     * @param userId - user id
     * @param isbns  - isbns of books
     * @param token  - token
     * @return String
     */
    public List<Isbn> postBooks(String userId, List<Isbn> isbns, String token) {
        return bookStoreApi.postBooks(userId, isbns, token);
    }

    /**
     * Get User
     *
     * @param userId - user id
     * @param token  - token
     * @return @User
     */
    public User getUser(String userId, String token) {
        return bookStoreApi.getUser(userId, token);
    }

    /**
     * Get user's books
     *
     * @param userId - user id
     * @param token  - token
     * @return {@link List<Book>}
     */
    public List<Book> getUserBooks(String userId, String token) {
        return getUser(userId, token).getBooks();
    }

}
