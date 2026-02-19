package org.gianni.quarkus.microservices.book;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(
        info = @Info(title = "BookAPI",
        description = "Creates books",
        version = "1.0"),
    tags = {
        @Tag(name = "api", description = "Public API"),
        @Tag(name = "books", description = "Interested in books")
    }
)
public class BookMicroservice extends Application { }
