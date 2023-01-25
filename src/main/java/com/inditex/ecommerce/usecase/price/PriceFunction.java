package com.inditex.ecommerce.usecase.price;

import com.inditex.ecommerce.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.domain.model.dto.SearchDTO;
import com.inditex.ecommerce.domain.model.entity.Price;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.usecase.price.mapper.PriceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Component
public class PriceFunction {

    Logger logger = LoggerFactory.getLogger(PriceFunction.class);

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceMapper priceMapper;

    public PriceDTO savePrice(PriceDTO priceDTO) {
        Price price = priceMapper.toDao(priceDTO);
        Price savedPrice = priceService.savePrice(price);
        logger.info("Price " + priceDTO + " saved successfully with id " + savedPrice.getPriceId());
        return priceMapper.toDTO(savedPrice);
    }

    public List<PriceDTO> fetchPriceList() {
        List<Price> priceList = priceService.fetchPriceList();
        return priceMapper.toDTO(priceList);
    }

    public PriceDTO updatePrice(PriceDTO priceDTO, Long priceId) {
        Price price = priceMapper.toDao(priceDTO);
        Price updatedPrice = priceService.updatePrice(price, priceId);
        logger.info("Price with id " + updatedPrice.getPriceId() + " updated successfully");
        return priceMapper.toDTO(updatedPrice);
    }

    public void deletePriceById(Long priceId) {
        priceService.deletePriceById(priceId);
        logger.info("Price with " + priceId + " deleted successfully");
    }

    public PriceDTO getPriceByBrandProductAndDate(SearchDTO search) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (Objects.nonNull(search.getDate()) && Objects.nonNull(search.getTime())) {
            localDateTime = LocalDateTime.of(search.getDate(),search.getTime());
        }

        logger.info("Search performed with next data: " + search.getBrandId() + " " + search.getProductId() + " " + localDateTime);
        Price price = priceService.getPriceByBrandProductAndDate(search.getBrandId(),
                search.getProductId(), localDateTime);
        logger.info("Search complete and return the next data " + price.toString());
        return priceMapper.toDTO(price);
    }

}
