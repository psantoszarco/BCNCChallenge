package com.inditex.ecommerce.domain.services;


import com.inditex.ecommerce.domain.model.entity.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    Price savePrice(Price price);
    List<Price> fetchPriceList();
    Price updatePrice(Price price, Long priceId);
    void deletePriceById(Long priceId);
    Price getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime date);
}
