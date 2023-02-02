package com.inditex.ecommerce.persistence_h2.adapters;

import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.persistence_h2.config.TestConfig;
import com.inditex.ecommerce.persistence_h2.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@TestConfig
public class PricePersistentTest {
    @MockBean
    private PricePersistence pricePersistence;

    @Test
    void testCreateSuccessful() {
        given(pricePersistence.create(any())).willReturn(Utils.getDefaultPrice());
        Price price = pricePersistence.create(any());
        verify(pricePersistence, times(1)).create(any());

        assertEquals(Utils.DEFAULT_ID_100, price.getPriceId());
        assertEquals(Utils.DEFAULT_BRAN_ID, price.getBrandId());
        assertEquals(Utils.DEFAULT_START_DATE, price.getStartDate());
        assertEquals(Utils.DEFAULT_END_DATE, price.getEndDate());
        assertEquals(Utils.DEFAULT_PRICE_LIST, price.getPriceList());
        assertEquals(Utils.DEFAULT_PRODUCT_ID, price.getProductId());
        assertEquals(Utils.DEFAULT_PRIORITY_4, price.getPriority());
        assertEquals(Utils.DEFAULT_PRICE, price.getPrice());
        assertEquals(Utils.DEFAULT_CURRENCY, price.getCurrency());
    }

    @Test
    void testCreateExpectedNotFoundExceptionThrown() {
        given(pricePersistence.create(any())).willThrow(PriceNotFoundException.class);

        assertThrows(PriceNotFoundException.class, () -> pricePersistence.create(any()));

        verify(pricePersistence, times(1)).create(any());
    }

    @Test
    void testReadAll() {
        given(pricePersistence.readAll()).willReturn(Stream.of(Utils.getDefaultPrices()));
        Stream<Price> prices = pricePersistence.readAll();
        verify(pricePersistence, times(1)).readAll();
        assertEquals(prices.count(), 3);
    }
}
