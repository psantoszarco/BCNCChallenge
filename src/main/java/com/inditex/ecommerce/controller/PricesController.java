package com.inditex.ecommerce.controller;

import com.inditex.ecommerce.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.domain.model.dto.SearchDTO;
import com.inditex.ecommerce.usecase.price.PriceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PricesController {

    Logger logger = LoggerFactory.getLogger(PricesController.class);

    @Autowired
    private PriceFunction priceFunction;

    @PostMapping("/prices")
    public PriceDTO savePrice(@RequestBody PriceDTO price)
    {
        logger.info("New price is going to be saved " + price.toString());
        return priceFunction.savePrice(price);
    }

    @GetMapping("/prices")
    public List<PriceDTO> fetchPriceList()
    {
        logger.info("Get all prices");
        return priceFunction.fetchPriceList();
    }

    @PutMapping("/prices/{id}")
    public PriceDTO updatePrice(@RequestBody PriceDTO price, @PathVariable("id") Long priceId)
    {
        logger.info("Price " + priceId + " is  going to be updated " + price.toString());
        return priceFunction.updatePrice(price, priceId);
    }

    @DeleteMapping("/prices/{id}")
    public String deletePriceById(@PathVariable("id") Long priceId)
    {
        logger.info("Price " + priceId + " is going to be deleted ");
        priceFunction.deletePriceById(priceId);
        return "Deleted Successfully";
    }

    @GetMapping("/getPrices")
    public PriceDTO getPriceByBrandProductAndDate(@RequestBody SearchDTO search) {
        logger.info("Perform a search by " + search.toString());
        return priceFunction.getPriceByBrandProductAndDate(search);

    }
}
