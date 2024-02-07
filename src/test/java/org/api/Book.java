package org.api;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Book {
    String isbn;
    String title;
    String subTitle;
    String author;
    Date publish_date;
    String publisher;
    Integer pages;
    String description;
    String website;

    public Book(String isbn, String title, String subTitle, String author,
                String publisher, Integer pages, String description, String website) {
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.website = website;
    }
}
