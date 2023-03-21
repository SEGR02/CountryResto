package ar.com.country.restaurant.config;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import java.util.Objects;
import java.util.Optional;

import static ar.com.country.restaurant.util.ApiDocsConstants.*;

@Configuration
public class SwaggerConfig {
    private static final String APPLICATION_JSON_VALUE = org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @Value("${application-description}")
    private String appDescription;

    @Value("${application-version}")
    private String appVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        final License license = new License()
                .name("Licensed under GNU GENERAL PUBLIC LICENSE, VERSION 3.0")
                .url("https://www.gnu.org/licenses/gpl-3.0.en.html");
        final Contact contact = new Contact()
                .name("Country restaurant team")
                .url("https://www.country-restaurant.com")
                .email("contact@countryrestaurant.com");
        final Info info = new Info()
                .title("Country Restaurant (Restless) API")
                .description("DISCLAIMER: " +
                        "Data security is not guaranteed. " +
                        "When using the application, do not enter any real personal information. " +
                        "You enter any personal information at your own risk.")
                .version(appVersion)
                .description(appDescription)
                .license(license)
                .contact(contact);
        final SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");
        final Components components = new Components()
                .addSecuritySchemes("JWT Token", securityScheme)
                .addResponses(BAD_REQUEST_RESPONSE_REF, errorResponse("Bad request"))
                .addResponses(NOT_FOUND_RESPONSE_REF, errorResponse("Resource not found"))
                .addResponses(UNAUTHORIZED_RESPONSE_REF, unauthorizedResponse())
                .addResponses(FORBIDDEN_RESPONSE_REF, forbiddenResponse())
                .addResponses(INTERNAL_SERVER_ERROR_RESPONSE_REF, internalServerErrorResponse());
        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList("JWT Token"));
    }

    private ApiResponse errorResponse(String description) {
        return new ApiResponse().content(
                        new Content()
                                .addMediaType(APPLICATION_JSON_VALUE, new MediaType().schema(problemSchema())))
                .description(description);
    }

    private ApiResponse unauthorizedResponse() {
        return new ApiResponse().content(new Content()
                        .addMediaType(APPLICATION_JSON_VALUE, new MediaType().schema(problemSchema())))
                .description("Authorization information is missing or invalid");
    }

    private ApiResponse forbiddenResponse() {
        return new ApiResponse().content(new Content()
                        .addMediaType(APPLICATION_JSON_VALUE, new MediaType().schema(problemSchema())))
                .description("The user doesn't have permission to access this resource");
    }

    private ApiResponse internalServerErrorResponse() {
        return new ApiResponse().content(new Content()).description("Internal server error");
    }

    private Schema problemSchema() {
        return ModelConverters.getInstance()
                .resolveAsResolvedSchema(new AnnotatedType(AbstractThrowableProblem.class)).schema;
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return (operation, handlerMethod) -> {
            Optional<PreAuthorize> preAuthorizeAnnotation = Optional.ofNullable(handlerMethod.getMethodAnnotation(PreAuthorize.class));
            StringBuilder sb = new StringBuilder();
            if (preAuthorizeAnnotation.isPresent()) {
                sb.append("This api requires **")
                        .append((preAuthorizeAnnotation.get()).value().replaceAll("hasRole|\\(|\\)|\\'", ""))
                        .append("** permission.");
            } else {
                sb.append("This api is **public**");
            }
            sb.append("<br /><br />");
            if (Objects.nonNull(operation.getDescription())) {
                sb.append(operation.getDescription());
            }
            operation.setDescription(sb.toString());
            return operation;
        };
    }

    @Bean
    public OpenApiCustomiser additionalSchemas() {
        return openApi -> {
            var statusTypeSchema = ModelConverters.getInstance()
                    .resolveAsResolvedSchema(new AnnotatedType(StatusType.class)).schema.name("StatusType");

            var throwableProblemSchema = ModelConverters.getInstance()
                    .resolveAsResolvedSchema(new AnnotatedType(ThrowableProblem.class)).schema.name("ThrowableProblem");

            var schemas = openApi.getComponents().getSchemas();
            schemas.put(statusTypeSchema.getName(), statusTypeSchema);
            schemas.put(throwableProblemSchema.getName(), throwableProblemSchema);
        };
    }

}
