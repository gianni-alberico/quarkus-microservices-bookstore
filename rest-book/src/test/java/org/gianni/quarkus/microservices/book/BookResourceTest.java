package org.gianni.quarkus.microservices.book;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.Mockito.when;

@QuarkusTest
class BookResourceTest {

    @InjectMock
    @RestClient
    NumberProxy numberProxy;

    @Test
    void shouldCreateABook() {
        String title = "My book";
        String author = "Gianni";
        int year = 2026;
        String genre = "IT";

        IsbnThirteen isbnThirteen = new IsbnThirteen();
        isbnThirteen.isbn13 = "13-mock";
        when(numberProxy.generateIsbnNumbers()).thenReturn(isbnThirteen);

        given()
            .formParam("title", title)
            .formParam("author", author)
            .formParam("year", year)
            .formParam("genre", genre)
        .when().post("/api/v1/books")
        .then()
            .statusCode(201)
            .body("isbn_13", startsWith("13-"))
            .body("title", is(title))
            .body("author", is(author))
            .body("year_of_publication", is(year))
            .body("genre", is(genre))
            .body("creation_date", startsWith("20"));
    }

}