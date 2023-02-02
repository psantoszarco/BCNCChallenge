package com.inditex.ecommerce.domain.models;

import com.googlecode.jmapper.annotations.JMap;
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
public class Price {
    @JMap
    private Long priceId;
    @JMap
    private Long brandId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JMap
    private LocalDateTime startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JMap
    private LocalDateTime endDate;
    @JMap
    private Long priceList;
    @JMap
    private Long productId;
    @JMap
    private Integer priority;
    @JMap
    private Double price;
    @JMap
    private String currency;
}
