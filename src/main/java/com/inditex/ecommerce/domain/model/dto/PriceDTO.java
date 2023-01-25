package com.inditex.ecommerce.domain.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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

}
