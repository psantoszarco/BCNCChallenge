package com.inditex.ecommerce.rest_web.config;

import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.rest_web.controllers.PricesController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class PricesWebConfig {

    @Bean
    public PricesController pricesController(PriceService priceService) {
        return new PricesController(priceService);
    }
}
