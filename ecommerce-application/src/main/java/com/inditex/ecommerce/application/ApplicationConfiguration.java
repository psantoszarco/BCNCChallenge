package com.inditex.ecommerce.application;

import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.domain.services.impl.PriceServiceImpl;
import com.inditex.ecommerce.persistence_h2.entities.PriceEntity;
import com.inditex.ecommerce.persistence_h2.repositories.PriceRepository;
import com.inditex.ecommerce.rest_web.controlleradvice.RestResponseEntityExceptionHandler;
import com.inditex.ecommerce.persistence_h2.adapters.PricePersistenceH2Adapter;
import com.inditex.ecommerce.rest_web.controllers.PricesController;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = {RestResponseEntityExceptionHandler.class})
@Configuration
@EntityScan(basePackageClasses = {PriceEntity.class})
@EnableJpaRepositories(basePackageClasses = { PriceRepository.class })
public class ApplicationConfiguration {

    @Bean
    public PriceService priceService(PricePersistence pricePersistence) {
        return new PriceServiceImpl(pricePersistence);
    }

    @Bean
    public PricePersistence pricePersistence(PriceRepository priceRepository) {
        return new PricePersistenceH2Adapter(priceRepository);
    }

    @Bean
    public PricesController pricesController(PriceService priceService) {
        return new PricesController(priceService);
    }

}
