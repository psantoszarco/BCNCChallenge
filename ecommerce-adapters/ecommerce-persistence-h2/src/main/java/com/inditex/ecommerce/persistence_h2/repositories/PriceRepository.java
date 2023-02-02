package com.inditex.ecommerce.persistence_h2.repositories;

import com.inditex.ecommerce.persistence_h2.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query(value = "select * from Prices p where p.brand_id = ?1 and p.product_id = ?2 and p.start_date <= ?3 and p.end_date > ?3 ORDER BY p.priority desc LIMIT 1", nativeQuery = true)
    Optional<PriceEntity> getProductByBrandIdProductIdAndDate(Long brandId, Long productId, LocalDateTime date);


}
