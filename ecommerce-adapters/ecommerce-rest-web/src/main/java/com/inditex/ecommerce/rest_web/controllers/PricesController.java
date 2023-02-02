package com.inditex.ecommerce.rest_web.controllers;

import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.rest_web.aop.RestLog;
import com.inditex.ecommerce.rest_web.dtos.PriceDTO;
import com.inditex.ecommerce.rest_web.dtos.SearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PricesController {

    Logger logger = LoggerFactory.getLogger(PricesController.class);

    static final String PRICES = "/prices";

    static final String PRICES_SEARCH = "/getPrices";

    static final String ID_MAPPING = "/{id}";

    @Autowired
    private PriceService priceService;

    public PricesController(PriceService priceService) {
        this.priceService = priceService;
    }

    @PostMapping("/prices")
    @RestLog(uri = "/prices")
    public Price savePrice(@RequestBody PriceDTO price)
    {
        logger.info("New price is going to be saved " + price.toString());
        return priceService.savePrice(price.toPrice());
    }

    @GetMapping("/prices")
    @RestLog(uri = "/prices")
    public List<Price> fetchPriceList()
    {
        logger.info("Get all prices");
        return priceService.fetchPriceList();
    }

    @PutMapping("/prices/{id}")
    @RestLog(uri = "/prices/{id}")
    public Price updatePrice(@RequestBody PriceDTO price, @PathVariable("id") Long priceId)
    {
        logger.info("Price " + priceId + " is  going to be updated " + price.toString());
        return priceService.updatePrice(price.toPrice(), priceId);
    }

    @DeleteMapping("/prices/{id}")
    @RestLog(uri = "/prices/{id}")
    public String deletePriceById(@PathVariable("id") Long priceId)
    {
        logger.info("Price " + priceId + " is going to be deleted ");
        priceService.deletePriceById(priceId);
        return "Deleted Successfully";
    }

    @GetMapping("/getPrices")
    @RestLog(uri = "/getPrices")
    public Price getPriceByBrandProductAndDate(@RequestBody SearchDTO search) {
        logger.info("Perform a search by " + search.toString());
        return priceService.getPriceByBrandProductAndDate(search.toSearch());

    }
}
