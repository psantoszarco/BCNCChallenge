package com.inditex.ecommerce.usecase;

import com.inditex.ecommerce.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.domain.model.dto.SearchDTO;
import com.inditex.ecommerce.domain.model.entity.Price;
import com.inditex.ecommerce.domain.services.PriceService;
import com.inditex.ecommerce.usecase.price.PriceFunction;
import com.inditex.ecommerce.usecase.price.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceFunctionTest {

    @Mock
    private PriceService priceService;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceFunction priceFunction;

    @Captor
    ArgumentCaptor<Price> priceCaptor;

    @Test
    public void saveTest() {
        //Set
        LocalDateTime now = LocalDateTime.now();
        PriceDTO priceDTO = generatePriceDTO(now);
        when(priceMapper.toDao(any(PriceDTO.class))).thenCallRealMethod();
        when(priceMapper.toDTO(any(Price.class))).thenCallRealMethod();
        when(priceService.savePrice(any(Price.class))).thenReturn(generatePrice(1L, LocalDateTime.now()));

        //Execution
        priceFunction.savePrice(priceDTO);

        //Assert
        verify(priceService).savePrice(priceCaptor.capture());
        Price toCheck = priceCaptor.getValue();
        assertNotNull(toCheck);
        assertNull(toCheck.getPriceId());
        assertEquals(1L, toCheck.getBrandId());
        assertEquals(now, toCheck.getStartDate());
        assertEquals(now, toCheck.getEndDate());
        assertEquals(2L, toCheck.getPriceList());
        assertEquals(33L, toCheck.getProductId());
        assertEquals(1, toCheck.getPriority());
        assertEquals(22.33D, toCheck.getPrice().doubleValue(), 0);
        assertEquals("EUR", toCheck.getCurrency());
    }

    @Test
    public void fetchTest() {
        //Set
        when(priceMapper.toDTO(any(List.class))).thenCallRealMethod();
        when(priceMapper.toDTO(any(Price.class))).thenCallRealMethod();
        when(priceService.fetchPriceList()).thenReturn(List.of(generatePrice(1L, LocalDateTime.now()), generatePrice(2L, LocalDateTime.now())));

        //Execution
        List<PriceDTO> toCheck = priceFunction.fetchPriceList();

        //Assert
        assertNotNull(toCheck);
        assertEquals(2, toCheck.size());
        assertEquals(1L, toCheck.get(0).getPriceId());
        assertEquals(2L, toCheck.get(1).getPriceId());
    }

    @Test
    public void updateTest() {
        //Set
        LocalDateTime now = LocalDateTime.now();
        PriceDTO priceDTO = generatePriceDTO(now);
        when(priceMapper.toDao(any(PriceDTO.class))).thenCallRealMethod();
        when(priceMapper.toDTO(any(Price.class))).thenCallRealMethod();
        when(priceService.updatePrice(any(Price.class), anyLong())).thenReturn(generatePrice(1L, LocalDateTime.now()));

        //Execution
        priceFunction.updatePrice(priceDTO, 1L);

        //Assert
        verify(priceService).updatePrice(priceCaptor.capture(), eq(1L));
        Price toCheck = priceCaptor.getValue();
        assertNotNull(toCheck);
        assertNull(toCheck.getPriceId());
        assertEquals(1L, toCheck.getBrandId());
        assertEquals(now, toCheck.getStartDate());
        assertEquals(now, toCheck.getEndDate());
        assertEquals(2L, toCheck.getPriceList());
        assertEquals(33L, toCheck.getProductId());
        assertEquals(1, toCheck.getPriority());
        assertEquals(22.33D, toCheck.getPrice().doubleValue(), 0);
        assertEquals("EUR", toCheck.getCurrency());
    }

    @Test
    public void deleteTest() {
        //Execution
        priceFunction.deletePriceById(1L);

        //Assert
        verify(priceService).deletePriceById(1L);
    }

    @Test
    public void getPriceByBrandProductAndDateTest() {
        //Set
        SearchDTO searchDTO = generateSearch();
        when(priceMapper.toDTO(any(Price.class))).thenCallRealMethod();
        when(priceService.getPriceByBrandProductAndDate(searchDTO.getBrandId(), searchDTO.getProductId(), LocalDateTime.of(searchDTO.getDate(), searchDTO.getTime()))).thenReturn(generatePrice(1L, LocalDateTime.now()));

        //Execution
        PriceDTO toCheck = priceFunction.getPriceByBrandProductAndDate(searchDTO);

        //Assert
        assertNotNull(toCheck);
    }

    private PriceDTO generatePriceDTO(LocalDateTime date) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(1L);
        priceDTO.setStartDate(date);
        priceDTO.setEndDate(date);
        priceDTO.setPriceList(2L);
        priceDTO.setProductId(33L);
        priceDTO.setPriority(1);
        priceDTO.setPrice(22.33D);
        priceDTO.setCurrency("EUR");
        return priceDTO;
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

    private SearchDTO generateSearch() {
        SearchDTO searchDTO = new SearchDTO();
        searchDTO.setProductId(1L);
        searchDTO.setBrandId(1L);
        searchDTO.setDate(LocalDate.now());
        searchDTO.setTime(LocalTime.MAX);
        return searchDTO;
    }


}
