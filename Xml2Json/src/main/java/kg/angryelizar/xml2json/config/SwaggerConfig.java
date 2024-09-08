package kg.angryelizar.xml2json.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("XML To JSON, API")
                .version("1.0")
                .description("Test task for technical interview.")
                .contact(new Contact().email("conovalov.elizar@gmail.com").name("Elizar"))
        );
    }
}
