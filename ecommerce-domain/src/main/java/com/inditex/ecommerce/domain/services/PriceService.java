package com.inditex.ecommerce.domain.services;



import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.models.Search;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PriceService {

    Price readById(final Long priceId);
    Price savePrice(Price price);
    List<Price> fetchPriceList();
    Price updatePrice(Price price, Long priceId);
    void deletePriceById(Long priceId);
    Price getPriceByBrandProductAndDate(Search search);
}
