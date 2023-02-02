package com.inditex.ecommerce.domain.config;

import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.domain.services.impl.PriceServiceImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.stream.Stream;

@Configuration
@EnableAutoConfiguration
public class PriceDomainConfig {

    @Bean
    public PriceService priceService(PricePersistence pricePersistence) {
        return new PriceServiceImpl(pricePersistence);
    }

    @Bean
    public PricePersistence pricePersistence() {
        return new PricePersistence() {
            @Override
            public Price getProductByBrandIdProductIdAndDate(Long brandId, Long productId, LocalDateTime date) {
                return null;
            }

            @Override
            public Stream<Price> readAll() {
                return null;
            }

            @Override
            public Price readById(Long aLong) {
                return null;
            }

            @Override
            public Price create(Price entity) {
                return null;
            }

            @Override
            public Price update(Price entity) {
                return null;
            }

            @Override
            public void deleteById(Long aLong) {

            }
        };
    }
}
