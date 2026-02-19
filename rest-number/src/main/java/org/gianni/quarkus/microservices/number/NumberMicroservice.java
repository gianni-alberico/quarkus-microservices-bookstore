package org.gianni.quarkus.microservices.number;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(
        info = @Info(
                title = "Number API",
                description = "Generates ISBN book numbers",
                version = "1.0"
        ),
        tags = {
                @Tag(name = "api", description = "Public API"),
                @Tag(name = "numbers", description = "Interested in numbers")
        }
)
public class NumberMicroservice extends Application { }
