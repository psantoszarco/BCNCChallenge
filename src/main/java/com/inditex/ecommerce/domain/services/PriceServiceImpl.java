package com.inditex.ecommerce.domain.services;

import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import com.inditex.ecommerce.domain.model.entity.Price;
import com.inditex.ecommerce.domain.repository.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService {

    Logger logger = LoggerFactory.getLogger(PriceService.class);

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Price savePrice(Price price)
    {
        return priceRepository.save(price);
    }

    @Override
    public List<Price> fetchPriceList()
    {
        return (List<Price>) priceRepository.findAll();
    }

    @Override
    public Price updatePrice(Price price, Long priceId)
    {
        Optional<Price> recoverPrice = priceRepository.findById(priceId);

        if (!recoverPrice.isPresent()) {
            logger.error("The price with id " + priceId + " is not found");
            throw new PriceNotFoundException("The price with id " + priceId + " is not found");
        }

        price.setPriceId(recoverPrice.get().getPriceId());
        return priceRepository.save(price);
    }

    @Override
    public void deletePriceById(Long priceId)
    {
        priceRepository.deleteById(priceId);
    }

    @Override
    public Price getPriceByBrandProductAndDate(Long brandId, Long productId, LocalDateTime date) {
        Optional<Price> price = priceRepository.
                getProductByBrandIdProductIdAndDate(brandId, productId, date);

        if (!price.isPresent()) {
            throw new PriceNotFoundException("Price not found");
        }
        return price.get();
    }
}
