package com.inditex.ecommerce.domain.ports;


import com.inditex.ecommerce.domain.models.Price;

import java.time.LocalDateTime;

public interface PricePersistence extends GenericPersistence<Price, Long> {
    Price getProductByBrandIdProductIdAndDate(Long brandId, Long productId, LocalDateTime date);

}
