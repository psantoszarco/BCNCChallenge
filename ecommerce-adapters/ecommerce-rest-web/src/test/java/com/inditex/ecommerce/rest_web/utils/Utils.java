package com.inditex.ecommerce.rest_web.utils;


import com.inditex.ecommerce.domain.models.Price;

import java.time.LocalDateTime;

public class Utils {

    public static final Long DEFAULT_ID_1 = 1L;
    public static final Long DEFAULT_ID_2 = 2L;

    public static final Long DEFAULT_ID_3 = 3L;

    public static final Long DEFAULT_BRAN_ID = 2L;
    public static final LocalDateTime DEFAULT_START_DATE = LocalDateTime.now();
    public static final LocalDateTime DEFAULT_END_DATE = LocalDateTime.now();
    public static final Long DEFAULT_PRICE_LIST = 3L;
    public static final Long DEFAULT_PRODUCT_ID = 4L;
    public static final Integer DEFAULT_PRIORITY = 1;
    public static final Integer DEFAULT_PRIORITY_2 = 2;
    public static final Integer DEFAULT_PRIORITY_3 = 3;
    public static final Double DEFAULT_PRICE = 55D;
    public static final String DEFAULT_CURRENCY = "EUR";

    public static Price getDefaultPrice() {
        return Price.builder()
                .priceId(DEFAULT_ID_1)
                .brandId(DEFAULT_BRAN_ID)
                .startDate(DEFAULT_START_DATE)
                .endDate(DEFAULT_END_DATE)
                .priceList(DEFAULT_PRICE_LIST)
                .productId(DEFAULT_PRODUCT_ID)
                .priority(DEFAULT_PRIORITY)
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
}
