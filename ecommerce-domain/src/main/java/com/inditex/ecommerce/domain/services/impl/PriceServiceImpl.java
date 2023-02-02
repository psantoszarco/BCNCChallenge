package com.inditex.ecommerce.domain.services.impl;

import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.models.Search;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.domain.services.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    Logger logger = LoggerFactory.getLogger(PriceService.class);

    @Autowired
    private PricePersistence pricePersistence;

    public PriceServiceImpl(PricePersistence pricePersistence) {
        this.pricePersistence = pricePersistence;
    }

    @Override
    public Price readById(final Long priceId) {
        return pricePersistence.readById(priceId);
    }

    @Override
    public Price savePrice(Price price)
    {
        return pricePersistence.create(price);
    }

    @Override
    public List<Price> fetchPriceList()
    {
        return pricePersistence.readAll().toList();
    }

    @Override
    public Price updatePrice(Price price, Long priceId)
    {
        Price recoverPrice = pricePersistence.readById(priceId);
        price.setPriceId(recoverPrice.getPriceId());
        return pricePersistence.update(price);
    }

    @Override
    public void deletePriceById(Long priceId)
    {
        pricePersistence.deleteById(priceId);
    }

    @Override
    public Price getPriceByBrandProductAndDate(Search search) {
        return pricePersistence.
                getProductByBrandIdProductIdAndDate(search.getBrandId(), search.getProductId(),
                        LocalDateTime.of(search.getDate(), search.getTime()));

    }
}
