package org.gianni.quarkus.microservices.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@Path("/books")
@Tag(name = "Book REST Endpoint")
public class BookResource {

    private static final Logger log = LoggerFactory.getLogger(BookResource.class);

    @Inject
    @RestClient
    NumberProxy numberProxy;

    @Inject
    org.jboss.logging.Logger logger;

    @Retry(delay = 1000, maxRetries = 2)
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a new book")
    public Response createABook(
            @FormParam("title") String title,
            @FormParam("author") String author,
            @FormParam("year") int yearOfPublication,
            @FormParam("genre") String genre) {
        Book book = new Book();
        book.setIsbn13(numberProxy.generateIsbnNumbers().getIsbn13());
        book.setTitle(title);
        book.setAuthor(author);
        book.setCreationDate(Instant.now());
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        log.info(String.format("Created book: %s", book));

        return Response.status(201).entity(book).build();
    }

    @SuppressWarnings("unused")
    Response fallbackOnCreatingABook(String title, String author, int yearOfPublication, String genre) {
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.setTitle(title);
        book.setAuthor(author);
        book.setCreationDate(Instant.now());
        book.setYearOfPublication(yearOfPublication);
        book.setGenre(genre);

        return Response.status(206).entity(book).build();
    }
}
