package com.inditex.ecommerce.persistence_h2.service;

import com.inditex.ecommerce.persistence_h2.entities.PriceEntity;
import com.inditex.ecommerce.persistence_h2.repositories.PriceRepository;
import com.inditex.ecommerce.persistence_h2.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class SeederService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeederService.class);

    private PriceRepository priceRepository;

    public SeederService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public void seedDatabase() {
        LOGGER.warn("Seeding the database");
        priceRepository.saveAll(
                Arrays.stream(Utils.getDefaultPrices())
                        .map(PriceEntity::fromPrice)
                        .collect(Collectors.toSet())
        );
    }

    public void seedDatabaseWithRealData() {
        LOGGER.warn("Seeding the database");
        priceRepository.saveAll(
                Arrays.stream(Utils.getRealDataPrices())
                        .map(PriceEntity::fromPrice)
                        .collect(Collectors.toSet())
        );
    }

    public void deleteAll() {
        LOGGER.warn("Deleting the records of the database");
        priceRepository.deleteAll();
    }
}