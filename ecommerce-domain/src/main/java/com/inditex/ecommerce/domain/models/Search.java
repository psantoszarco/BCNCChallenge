package com.inditex.ecommerce.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Search {
    private Long brandId;
    private Long productId;
    private LocalDate date;
    private LocalTime time;
}
