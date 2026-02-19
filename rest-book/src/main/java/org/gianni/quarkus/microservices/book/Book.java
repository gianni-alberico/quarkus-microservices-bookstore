package org.gianni.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "A book")
public class Book {

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    public String isbn13;

    @Schema(required = true)
    public String title;

    public String author;

    @JsonbProperty("year_of_publication")
    public int yearOfPublication;

    public String genre;

    @JsonbDateFormat("yyyy-MM-dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String.class, format = "date")
    public Instant creationDate;

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", genre='" + genre + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

}
