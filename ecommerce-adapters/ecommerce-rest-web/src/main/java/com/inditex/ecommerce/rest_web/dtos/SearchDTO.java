package com.inditex.ecommerce.rest_web.dtos;

import com.inditex.ecommerce.domain.models.Search;
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

    public Search toSearch() {
        return Search.builder()
                .brandId(getBrandId())
                .productId(getProductId())
                .date(getDate())
                .time(getTime())
                .build();
    }
}
