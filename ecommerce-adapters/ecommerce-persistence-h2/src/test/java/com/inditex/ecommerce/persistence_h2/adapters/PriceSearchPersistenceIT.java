package com.inditex.ecommerce.persistence_h2.adapters;

import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.persistence_h2.config.TestConfig;
import com.inditex.ecommerce.persistence_h2.service.SeederService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfig
public class PriceSearchPersistenceIT {
    @Autowired
    private PricePersistence pricePersistence;

    @BeforeAll
    static void populateDatabase(@Autowired SeederService seederService) {
        seederService.seedDatabaseWithRealData();
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_10_00() throws Exception {
        Price price = pricePersistence.getProductByBrandIdProductIdAndDate(1L, 35455L,
                LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(10, 0, 0)));

        assertEquals(1L, price.getBrandId());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(0, 0)), price.getStartDate());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)), price.getEndDate());
        assertEquals(1L, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(0, price.getPriority());
        assertEquals(35.5D, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_18_00() throws Exception {
        Price price = pricePersistence.getProductByBrandIdProductIdAndDate(1L, 35455L,
                LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(18, 0, 0)));

        assertEquals(1L, price.getBrandId());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(15, 0)), price.getStartDate());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(18, 30)), price.getEndDate());
        assertEquals(2L, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(25.45D, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void should_retrieve_a_price_in_14_06_at_21_00() throws Exception {
        Price price = pricePersistence.getProductByBrandIdProductIdAndDate(1L, 35455L,
                LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(21, 0, 0)));

        assertEquals(1L, price.getBrandId());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(0, 0)), price.getStartDate());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)), price.getEndDate());
        assertEquals(1L, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(0, price.getPriority());
        assertEquals(35.5D, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void should_retrieve_a_price_in_15_06_at_10_00() throws Exception {
        Price price = pricePersistence.getProductByBrandIdProductIdAndDate(1L, 35455L,
                LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(10, 0, 0)));

        assertEquals(1L, price.getBrandId());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(0, 0)), price.getStartDate());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(11, 0)), price.getEndDate());
        assertEquals(3L, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(30.5D, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void should_retrieve_a_price_in_16_06_at_21_00() throws Exception {
        Price price = pricePersistence.getProductByBrandIdProductIdAndDate(1L, 35455L,
                LocalDateTime.of(LocalDate.of(2020, 06, 16), LocalTime.of(21, 0, 0)));

        assertEquals(1L, price.getBrandId());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(16, 0)), price.getStartDate());
        assertEquals(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)), price.getEndDate());
        assertEquals(4L, price.getPriceList());
        assertEquals(35455L, price.getProductId());
        assertEquals(1, price.getPriority());
        assertEquals(38.95D, price.getPrice());
        assertEquals("EUR", price.getCurrency());
    }
}
