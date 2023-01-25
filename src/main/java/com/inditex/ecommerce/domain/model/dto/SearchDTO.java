package com.inditex.ecommerce.domain.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class SearchDTO {
    private Long brandId;
    private Long productId;
    private LocalDate date;
    private LocalTime time;
}
