package org.gianni.quarkus.microservices.number;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Random;
import java.util.logging.Logger;

@Path("/numbers")
@Tag(name = "Number REST Endpoint")
public class NumberResource {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(NumberResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generates book numbers")
    public IsbnNumbers generateIsbnNumbers() {
        IsbnNumbers isbnNumbers = new IsbnNumbers();
        isbnNumbers.setIsbn13("13-" + new Random().nextInt(100_000_000));
        isbnNumbers.setIsbn10("10-" + new Random().nextInt(100_000));
        isbnNumbers.setGenerationDate(Instant.now());

        log.info(String.format("Numbers generated %s", isbnNumbers));
        return isbnNumbers;
    }
}
