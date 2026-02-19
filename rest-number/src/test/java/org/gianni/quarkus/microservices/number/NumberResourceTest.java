package org.gianni.quarkus.microservices.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class NumberResourceTest {
    @Test
    void shouldGenerateISBN13() {
        given()
          .when().get("/api/v1/numbers")
          .then()
             .statusCode(200)
             .body("isbn_13", startsWith("13-"))
             .body("isbn_10", startsWith("10-"))
             .body("keySet().size()", is(2));
    }

}