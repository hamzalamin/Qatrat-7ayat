package com.wora.qatrat7ayat.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI defineApi(){
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("Hamza LAMIN");
        contact.setEmail("hamzalamin80@gmail.com");
        contact.setUrl("https://www.linkedin.com/in/hamza-lamin-a0440a296/");

        Info info = new Info()
                .title("API REST Qatrat Hayat")
                .version("1.0")
                .description("Plateforme web de gestion des dons de sang permettant de connecter donneurs, " +
                        "demandeurs et entreprises soutenant les initiatives solidaires. Suivi des besoins, " +
                        "cartographie des centres, et gestion simplifiée des profils pour sauver des vies")
                .contact(contact);
        return new OpenAPI().info(info).servers(List.of(server));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}