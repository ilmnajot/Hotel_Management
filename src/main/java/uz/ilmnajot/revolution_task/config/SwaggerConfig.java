package uz.ilmnajot.revolution_task.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(info = @Info(
        title = "Hotel Management",
        version = "1.0",
        description = "OpenAPI documentation for Hotel Management",
        contact = @Contact(name = "SamPM", email = "user@gmail.com")
),
        security = @SecurityRequirement(
                name = "BearerAuth"
        )
//        servers = {
//        @Server(url = "http://localhost:8080", description = "Education server"),
//}
)
@SecurityScheme(
        name = "BearerAuth",
        description = "JWT-based authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {

}
