package org.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Isbn {
    String isbn;

    public Isbn(String isbn) {
        this.isbn = isbn;
    }
}
