package org.gianni.quarkus.microservices.book;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "number-proxy")
@Path("/api/v1/")
@ApplicationScoped
public interface NumberProxy {

    @GET
    @Path("numbers")
    @Produces(MediaType.APPLICATION_JSON)
    IsbnThirteen generateIsbnNumbers();
}