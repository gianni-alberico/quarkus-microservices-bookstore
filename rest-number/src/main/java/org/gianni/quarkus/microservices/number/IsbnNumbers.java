package org.gianni.quarkus.microservices.number;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Several ISBN formats")
public class IsbnNumbers {
    @Schema(required = true)
    @JsonbProperty("isbn_10")
    public String isbn10;

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    public String isbn13;

    @JsonbTransient
    public Instant generationDate;

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public void setGenerationDate(Instant generationDate) {
        this.generationDate = generationDate;
    }

    @Override
    public String toString() {
        return String.format("""
                ISBN10: %s
                ISBN13: %s
                GenerationDate: %S
                """, isbn10, isbn13, generationDate);
    }
}
