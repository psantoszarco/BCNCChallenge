package com.inditex.ecommerce.usecase.price.mapper;

import com.inditex.ecommerce.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.domain.model.entity.Price;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PriceMapperTest {

    PriceMapper priceMapper = new PriceMapper();

    @Test
    public void toDTOTest() {
        //Set
        LocalDateTime now = LocalDateTime.now();
        Price price = generatePrice(123L, now);

        //Execution
        PriceDTO toCheck = priceMapper.toDTO(price);

        //Assert
        assertEquals(123L, toCheck.getPriceId().longValue());
        assertEquals(1L, toCheck.getBrandId().longValue());
        assertEquals(now, toCheck.getStartDate());
        assertEquals(now, toCheck.getEndDate());
        assertEquals(2L, toCheck.getPriceList().longValue());
        assertEquals(33L, toCheck.getProductId().longValue());
        assertEquals(1, toCheck.getPriority().intValue());
        assertEquals(22.33D, toCheck.getPrice().doubleValue(), 0);
        assertEquals("EUR", toCheck.getCurrency());
    }

    @Test
    public void toDaoTest() {
        //Set
        LocalDateTime now = LocalDateTime.now();
        Price price = generatePrice(123L, now);

        //Execution
        PriceDTO priceDTO = priceMapper.toDTO(price);
        Price toCheck = priceMapper.toDao(priceDTO);

        //Assert
        assertEquals(price, toCheck);
    }

    @Test
    public void toDTOListTest() {
        //Set
        List<Price> priceList = List.of(generatePrice(111L, LocalDateTime.now()), generatePrice(222L, LocalDateTime.now()));

        //Execution
        List<PriceDTO> toCheck = priceMapper.toDTO(priceList);

        //Assert
        assertEquals(2, toCheck.size());
        assertEquals(111L, toCheck.get(0).getPriceId().longValue());
        assertEquals(222L, toCheck.get(1).getPriceId().longValue());
    }

    private Price generatePrice(Long id, LocalDateTime date) {
        Price price = new Price();
        price.setPriceId(id);
        price.setBrandId(1L);
        price.setStartDate(date);
        price.setEndDate(date);
        price.setPriceList(2L);
        price.setProductId(33L);
        price.setPriority(1);
        price.setPrice(22.33D);
        price.setCurrency("EUR");
        return price;
    }
}
