package com.inditex.ecommerce.persistence_h2.entities;

import com.inditex.ecommerce.domain.models.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "brandId", "startDate", "endDate", "priceList", "productId", "priority" })
})
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PriceEntity {

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

    public static PriceEntity fromPrice(Price price) throws ClassCastException {
        return PriceEntity.builder()
                .priceId(price.getPriceId())
                .brandId(price.getBrandId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .priceList(price.getPriceList())
                .productId(price.getProductId())
                .priority(price.getPriority())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .build();
    }

    public Price toPrice() throws ClassCastException {
        return Price.builder()
                .priceId(this.getPriceId())
                .brandId(this.getBrandId())
                .startDate(this.getStartDate())
                .endDate(this.getEndDate())
                .priceList(this.getPriceList())
                .productId(this.getProductId())
                .priority(this.getPriority())
                .price(this.getPrice())
                .currency(this.getCurrency())
                .build();
    }

}
