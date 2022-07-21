package me.jiniworld.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.servers.Server;
import me.jiniworld.demo.util.MessageUtils;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion, @Value("${demo.url}") String url,
            @Value("${spring.profiles.active}") String active) {
        Info info = new Info().title("Demo API - " + active).version(appVersion)
                .description("Spring Data JPA Tutorial")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("jini").url("https://blog.jiniworld.me/").email("jini@jiniworld.me"))
                .license(new License().name("Apache License Version 2.0").url("http://www.apache.org/licenses/LICENSE-2.0"));

        return new OpenAPI()
                .components(new Components())
                .info(info)
                .servers(Arrays.asList(new Server().url(url).description("demo (" + active +")")));
    }

    @Bean
    public OpenApiCustomiser consumerTypeHeaderOpenAPICustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.getResponses().addApiResponse("400", new ApiResponse().description(MessageUtils.FAIL)
                        .content(new Content().addMediaType("application/json",
                                new MediaType().schema(new Schema().$ref("#/components/schemas/BaseResponse"))))));
    }

}
