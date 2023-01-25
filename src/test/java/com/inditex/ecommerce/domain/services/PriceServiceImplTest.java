package com.inditex.ecommerce.domain.services;

import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import com.inditex.ecommerce.domain.model.entity.Price;
import com.inditex.ecommerce.domain.repository.PriceRepository;
import com.inditex.ecommerce.domain.services.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    public void savePriceTest() {
        //Set
        Price price = generatePrice(null, LocalDateTime.now());

        //Execution
        priceService.savePrice(price);

        //Assert
        verify(priceRepository).save(price);
    }

    @Test
    public void fetchPriceListTest() {
        //Execution
        priceService.fetchPriceList();

        //Assert
        verify(priceRepository).findAll();
    }

    @Test
    public void updatePriceTest() {
        //Set
        LocalDateTime now = LocalDateTime.now();
        Price price = generatePrice(null, now);
        Price databasePrice = generatePrice(1L, now);
        when(priceRepository.findById(1L)).thenReturn(Optional.of(databasePrice));

        //Execution
        priceService.updatePrice(price, 1L);

        //Assert
        price.setPriceId(databasePrice.getPriceId());
        verify(priceRepository).save(price);
    }

    @Test
    public void updateNotExistentPriceTest() {
        //Set
        Price price = generatePrice(null, LocalDateTime.now());
        when(priceRepository.findById(1L)).thenReturn(Optional.empty());

        //Execution
        try {
            priceService.updatePrice(price, 1L);
            fail("Expected PriceNotFoundException");
        } catch (PriceNotFoundException e) {
            //Assert
            assertEquals("The price with id 1 is not found", e.getMessage());
        } catch (Exception ex) {
            fail("Expected PriceNotFoundException and found " + ex.getMessage());
        }
    }

    @Test
    public void deletePriceByIdTest() {
        //Execution
        priceService.deletePriceById(1L);

        //Assert
        verify(priceRepository).deleteById(1L);
    }

    @Test
    public void getPriceByBrandProductAndDateTest() {
        //Set
        Long brand_id = 1L;
        Long product_id = 1L;
        LocalDateTime now = LocalDateTime.now();
        when(priceRepository.getProductByBrandIdProductIdAndDate(brand_id, product_id, now)).thenReturn(Optional.of(generatePrice(1L, LocalDateTime.now())));

        //Execution
        priceService.getPriceByBrandProductAndDate(brand_id, product_id, now);

        //Assert
        verify(priceRepository).getProductByBrandIdProductIdAndDate(brand_id, product_id, now);
    }

    @Test
    public void getNoReturnedPriceByBrandProductAndDateTest() {
        //Set
        Long brand_id = 1L;
        Long product_id = 1L;
        LocalDateTime now = LocalDateTime.now();
        when(priceRepository.getProductByBrandIdProductIdAndDate(brand_id, product_id, now)).thenReturn(Optional.empty());

        //Execution
        try {
            priceService.getPriceByBrandProductAndDate(brand_id, product_id, now);
            fail("Non reachable code, expected PriceNotFoundException");
        } catch (PriceNotFoundException e) {
            assertEquals("Price not found", e.getMessage());
        } catch (Exception ex) {
            fail("Expected PriceNotFoundException, obtained " + ex.getCause());
        }
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
