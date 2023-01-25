package com.inditex.ecommerce.usecase.price.mapper;

import com.inditex.ecommerce.domain.model.dto.PriceDTO;
import com.inditex.ecommerce.domain.model.entity.Price;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceMapper {

    public Price toDao(@NotNull PriceDTO priceDTO) {
        Price price = new Price();
        price.setPriceId(priceDTO.getPriceId());
        price.setBrandId(priceDTO.getBrandId());
        price.setStartDate(priceDTO.getStartDate());
        price.setEndDate(priceDTO.getEndDate());
        price.setPriceList(priceDTO.getPriceList());
        price.setProductId(priceDTO.getProductId());
        price.setPriority(priceDTO.getPriority());
        price.setPrice(priceDTO.getPrice());
        price.setCurrency(priceDTO.getCurrency());

        return price;
    }

    public PriceDTO toDTO(@NotNull Price price) {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setPriceId(price.getPriceId());
        priceDTO.setBrandId(price.getBrandId());
        priceDTO.setStartDate(price.getStartDate());
        priceDTO.setEndDate(price.getEndDate());
        priceDTO.setPriceList(price.getPriceList());
        priceDTO.setProductId(price.getProductId());
        priceDTO.setPriority(price.getPriority());
        priceDTO.setPrice(price.getPrice());
        priceDTO.setCurrency(price.getCurrency());
        return priceDTO;
    }

    public List<PriceDTO> toDTO(@NotNull List<Price> priceList) {
        return priceList.stream().map(this::toDTO).toList();
    }
}
