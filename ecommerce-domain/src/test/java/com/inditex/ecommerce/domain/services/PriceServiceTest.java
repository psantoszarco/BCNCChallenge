package com.inditex.ecommerce.domain.services;

import com.inditex.ecommerce.domain.config.TestConfig;
import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.models.Search;
import com.inditex.ecommerce.domain.services.impl.PriceServiceImpl;
import com.inditex.ecommerce.domain.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@TestConfig
class PriceServiceTest {

    @MockBean
    private PriceServiceImpl priceService;

    @Test
    void testCreate() {
        Price expectedPrice = Utils.getDefaultPrice();
        given(priceService.savePrice(any())).willReturn(expectedPrice);
        Price price = priceService.savePrice(any());
        verify(priceService, times(1)).savePrice(any());

        assertEquals(expectedPrice.getPriceId(), price.getPriceId());
        assertEquals(expectedPrice.getBrandId(), price.getBrandId());
        assertEquals(expectedPrice.getStartDate(), price.getStartDate());
        assertEquals(expectedPrice.getEndDate(), price.getEndDate());
        assertEquals(expectedPrice.getPriceList(), price.getPriceList());
        assertEquals(expectedPrice.getProductId(), price.getProductId());
        assertEquals(expectedPrice.getPriority(), price.getPriority());
        assertEquals(expectedPrice.getPrice(), price.getPrice());
        assertEquals(expectedPrice.getCurrency(), price.getCurrency());
    }

    @Test
    public void fetchPriceListTest() {
        //Execution
        priceService.fetchPriceList();

        //Assert
        verify(priceService).fetchPriceList();
    }

    @Test
    public void updatePriceTest() {
        Price expectedPrice = Utils.getDefaultPrice();
        given(priceService.updatePrice(any(), any())).willReturn(expectedPrice);
        priceService.updatePrice(any(), eq(Utils.DEFAULT_ID));
        verify(priceService, times(1)).updatePrice(any(), any());
    }

    @Test
    public void deletePriceByIdTest() {
        //Execution
        priceService.deletePriceById(Utils.DEFAULT_ID);

        //Assert
        verify(priceService).deletePriceById(Utils.DEFAULT_ID);
    }

    @Test
    public void getPriceByBrandProductAndDateTest() {
        //Set
        Long brand_id = 1L;
        Long product_id = 1L;
        LocalDate now = LocalDate.now();
        LocalTime time = LocalTime.now();
        when(priceService.getPriceByBrandProductAndDate(any())).thenReturn(Utils.getDefaultPrice());

        //Execution
        priceService.getPriceByBrandProductAndDate(getSearch(brand_id, product_id, now, time));

        //Assert
        verify(priceService).getPriceByBrandProductAndDate(getSearch(brand_id, product_id, now, time));
    }

    private Search getSearch(Long brandId, Long productId, LocalDate date, LocalTime time) {
        Search search = new Search();
        search.setBrandId(brandId);
        search.setProductId(productId);
        search.setDate(date);
        search.setTime(time);
        return search;

    }
}
