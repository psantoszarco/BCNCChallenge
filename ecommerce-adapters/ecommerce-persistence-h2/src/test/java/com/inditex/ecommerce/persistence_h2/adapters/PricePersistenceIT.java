package com.inditex.ecommerce.persistence_h2.adapters;

import com.inditex.ecommerce.domain.exceptions.ConflictException;
import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.persistence_h2.config.TestConfig;
import com.inditex.ecommerce.persistence_h2.service.SeederService;
import com.inditex.ecommerce.persistence_h2.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.junit.jupiter.api.Assertions.*;


@TestConfig
public class PricePersistenceIT {
    @Autowired
    private PricePersistence pricePersistence;

    @BeforeAll
    static void populateDatabase(@Autowired SeederService seederService) {
        seederService.seedDatabase();
    }

    @Test
    void testCreateSuccessful() {
        Price expectedPrice = Utils.getDefaultPrice();
        Price price = pricePersistence.create(expectedPrice);

        assertEquals(expectedPrice.getBrandId(), price.getBrandId());
        assertEquals(expectedPrice.getStartDate(), price.getStartDate());
        assertEquals(expectedPrice.getEndDate(), price.getEndDate());
        assertEquals(expectedPrice.getPriceList(), price.getPriceList());
        assertEquals(expectedPrice.getProductId(), price.getProductId());
        assertEquals(expectedPrice.getPriority(), price.getPriority());
        assertEquals(expectedPrice.getPrice(), price.getPrice());
        assertEquals(expectedPrice.getCurrency(), price.getCurrency());
    }

    @Test
    void testCreateExpectedConflictExceptionThrown() {
        Price price = Utils.getDefaultPrices()[1];

        assertThrows(ConflictException.class, () -> pricePersistence.create(price));
    }

    @Test
    void testCreateExpectedInvalidDataAccessApiUsageExceptionThrown() {
        Price price = Utils.getDefaultPrice();
        price.setPriceId(null);

        assertThrows(InvalidDataAccessApiUsageException.class, () -> pricePersistence.create(price));
    }

    @Test
    void testReadByIdSuccessful() {
        Price expectedPrice = Utils.getDefaultPrices()[0];
        Price obtainedPrice = pricePersistence.readById(expectedPrice.getPriceId());

        assertEquals(expectedPrice.getPriceId(), obtainedPrice.getPriceId());
        assertEquals(expectedPrice.getBrandId(), obtainedPrice.getBrandId());
        assertNotNull(obtainedPrice.getStartDate());
        assertNotNull(obtainedPrice.getEndDate());
        assertEquals(expectedPrice.getPriceList(), obtainedPrice.getPriceList());
        assertEquals(expectedPrice.getProductId(), obtainedPrice.getProductId());
        assertEquals(expectedPrice.getPriority(), obtainedPrice.getPriority());
        assertEquals(expectedPrice.getPrice(), obtainedPrice.getPrice());
        assertEquals(expectedPrice.getCurrency(), obtainedPrice.getCurrency());
    }

    @Test
    void testReadByIdExpectedNotFoundExceptionThrown() {
        Long notExistsId = 999L;

        assertThrows(PriceNotFoundException.class, () -> pricePersistence.readById(notExistsId));
    }
}
