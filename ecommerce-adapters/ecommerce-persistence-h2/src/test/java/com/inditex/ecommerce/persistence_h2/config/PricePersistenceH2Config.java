package com.inditex.ecommerce.persistence_h2.config;

import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.persistence_h2.adapters.PricePersistenceH2Adapter;
import com.inditex.ecommerce.persistence_h2.entities.PriceEntity;
import com.inditex.ecommerce.persistence_h2.repositories.PriceRepository;
import com.inditex.ecommerce.persistence_h2.service.SeederService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {PriceRepository.class})
@EntityScan(basePackageClasses = {PriceEntity.class})
@EnableAutoConfiguration
public class PricePersistenceH2Config {

    @Bean
    public PricePersistence pricePersistence(PriceRepository priceRepository) {
        return new PricePersistenceH2Adapter(priceRepository);
    }

    @Bean
    public SeederService seederService(PriceRepository priceRepository) {
        return new SeederService(priceRepository);
    }

}

