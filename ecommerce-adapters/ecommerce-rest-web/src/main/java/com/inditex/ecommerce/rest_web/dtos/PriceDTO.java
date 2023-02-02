package com.inditex.ecommerce.rest_web.dtos;

import com.inditex.ecommerce.domain.models.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceDTO {
    private Long priceId;
    private Long brandId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;

    public Price toPrice() {
        return Price.builder()
                .priceId(getPriceId())
                .brandId(getBrandId())
                .startDate(getStartDate())
                .endDate(getEndDate())
                .priceList(getPriceList())
                .productId(getProductId())
                .priority(getPriority())
                .price(getPrice())
                .currency(getCurrency())
                .build();
    }
}
