package com.inditex.ecommerce.domain.repository;

import com.inditex.ecommerce.domain.model.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("select p from Price p where p.brandId = :brandId and p.productId = :productId and p.startDate <= :date and p.endDate > :date ORDER BY p.priority desc LIMIT 1")
    Optional<Price> getProductByBrandIdProductIdAndDate(Long brandId, Long productId, LocalDateTime date);


}
