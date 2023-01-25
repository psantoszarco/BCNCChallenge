package com.inditex.ecommerce.domain.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "Prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "brandId", "startDate", "endDate", "priceList", "productId", "priority" })
})
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long priceId;
    private Long brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long priceList;
    private Long productId;
    private Integer priority;
    private Double price;
    private String currency;

}
