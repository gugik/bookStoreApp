package org.api;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {
    String userID;
    String username;
    List<Book> books;
}
