package home.production.msscbeerservice.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Value("${sfg.brewery.inventory-username}") String inventoryUsername,
                                                                   @Value("${sfg.brewery.inventory-password}") String inventoryPassword) {
        return new BasicAuthRequestInterceptor(inventoryUsername, inventoryPassword);
    }
}
