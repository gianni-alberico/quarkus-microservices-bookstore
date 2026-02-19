package org.gianni.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbProperty;

public class IsbnThirteen {
    @JsonbProperty("isbn_13")
    public String isbn13;

    public String getIsbn13() {
        return isbn13;
    }
}
