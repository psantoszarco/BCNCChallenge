package com.inditex.ecommerce.persistence_h2.utils;


import com.inditex.ecommerce.domain.models.Price;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utils {

    public static final Long DEFAULT_ID_1 = 1L;
    public static final Long DEFAULT_ID_2 = 2L;
    public static final Long DEFAULT_ID_3 = 3L;
    public static final Long DEFAULT_ID_100 = 100L;
    public static final Long DEFAULT_BRAN_ID = 2L;
    public static final LocalDateTime DEFAULT_START_DATE = LocalDateTime.now();
    public static final LocalDateTime DEFAULT_END_DATE = LocalDateTime.now();
    public static final Long DEFAULT_PRICE_LIST = 3L;
    public static final Long DEFAULT_PRODUCT_ID = 4L;
    public static final Integer DEFAULT_PRIORITY = 1;
    public static final Integer DEFAULT_PRIORITY_2 = 2;
    public static final Integer DEFAULT_PRIORITY_3 = 3;
    public static final Integer DEFAULT_PRIORITY_4 = 4;
    public static final Double DEFAULT_PRICE = 55D;
    public static final String DEFAULT_CURRENCY = "EUR";

    public static Price getDefaultPrice() {
        return Price.builder()
                .priceId(DEFAULT_ID_100)
                .brandId(DEFAULT_BRAN_ID)
                .startDate(DEFAULT_START_DATE)
                .endDate(DEFAULT_END_DATE)
                .priceList(DEFAULT_PRICE_LIST)
                .productId(DEFAULT_PRODUCT_ID)
                .priority(DEFAULT_PRIORITY_4)
                .price(DEFAULT_PRICE)
                .currency(DEFAULT_CURRENCY)
                .build();
    }

    public static Price[] getDefaultPrices() {
        return new Price[]{
                Price.builder()
                        .priceId(DEFAULT_ID_1)
                        .brandId(DEFAULT_BRAN_ID)
                        .startDate(DEFAULT_START_DATE)
                        .endDate(DEFAULT_END_DATE)
                        .priceList(DEFAULT_PRICE_LIST)
                        .productId(DEFAULT_PRODUCT_ID)
                        .priority(DEFAULT_PRIORITY)
                        .price(DEFAULT_PRICE)
                        .currency(DEFAULT_CURRENCY)
                        .build(),
                Price.builder()
                        .priceId(DEFAULT_ID_2)
                        .brandId(DEFAULT_BRAN_ID)
                        .startDate(DEFAULT_START_DATE)
                        .endDate(DEFAULT_END_DATE)
                        .priceList(DEFAULT_PRICE_LIST)
                        .productId(DEFAULT_PRODUCT_ID)
                        .priority(DEFAULT_PRIORITY_2)
                        .price(DEFAULT_PRICE)
                        .currency(DEFAULT_CURRENCY)
                        .build(),
                Price.builder()
                        .priceId(DEFAULT_ID_3)
                        .brandId(DEFAULT_BRAN_ID)
                        .startDate(DEFAULT_START_DATE)
                        .endDate(DEFAULT_END_DATE)
                        .priceList(DEFAULT_PRICE_LIST)
                        .productId(DEFAULT_PRODUCT_ID)
                        .priority(DEFAULT_PRIORITY_3)
                        .price(DEFAULT_PRICE)
                        .currency(DEFAULT_CURRENCY)
                        .build()
        };
    }

    public static Price[] getRealDataPrices() {
        return new Price[]{
                Price.builder()
                        .priceId(95L)
                        .brandId(1L)
                        .startDate(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(0, 0, 0)))
                        .endDate(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)))
                        .priceList(1L)
                        .productId(35455L)
                        .priority(0)
                        .price(35.50D)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .priceId(96L)
                        .brandId(1L)
                        .startDate(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(15, 0, 0)))
                        .endDate(LocalDateTime.of(LocalDate.of(2020, 06, 14), LocalTime.of(18, 30, 0)))
                        .priceList(2L)
                        .productId(35455L)
                        .priority(1)
                        .price(25.45D)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .priceId(97L)
                        .brandId(1L)
                        .startDate(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(0, 0, 0)))
                        .endDate(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(11, 0, 0)))
                        .priceList(3L)
                        .productId(35455L)
                        .priority(1)
                        .price(30.50D)
                        .currency("EUR")
                        .build(),
                Price.builder()
                        .priceId(98L)
                        .brandId(1L)
                        .startDate(LocalDateTime.of(LocalDate.of(2020, 06, 15), LocalTime.of(16, 0, 0)))
                        .endDate(LocalDateTime.of(LocalDate.of(2020, 12, 31), LocalTime.of(23, 59, 59)))
                        .priceList(4L)
                        .productId(35455L)
                        .priority(1)
                        .price(38.95D)
                        .currency("EUR")
                        .build()
        };
    }
}
