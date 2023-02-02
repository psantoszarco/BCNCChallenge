package com.inditex.ecommerce.persistence_h2.adapters;

import com.inditex.ecommerce.domain.exceptions.ConflictException;
import com.inditex.ecommerce.domain.exceptions.PriceNotFoundException;
import com.inditex.ecommerce.domain.models.Price;
import com.inditex.ecommerce.domain.ports.PricePersistence;
import com.inditex.ecommerce.persistence_h2.entities.PriceEntity;
import com.inditex.ecommerce.persistence_h2.repositories.PriceRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.stream.Stream;

@Transactional
public class PricePersistenceH2Adapter implements PricePersistence {

    private PriceRepository priceRepository;

    public PricePersistenceH2Adapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Stream<Price> readAll() {
        return priceRepository.findAll()
                .stream()
                .findAny().map(e -> priceRepository.findAll().stream().map(PriceEntity::toPrice))
                .orElseThrow(() -> new PriceNotFoundException("Price not found"));

    }

    @Override
    public Price readById(Long id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new PriceNotFoundException("Price id: " + id))
                .toPrice();
    }

    @Override
    public Price create(@Valid Price price) {
        if (price.getPriceId() != null) {
            assertIdNotExist(price.getPriceId());
        }

        return priceRepository.save(PriceEntity.fromPrice(price))
                .toPrice();
    }

    @Override
    public Price update(@Valid Price price) {
        return priceRepository.save(PriceEntity.fromPrice(price))
                .toPrice();
    }

    @Override
    public void deleteById(Long id) {
        priceRepository.deleteById(id);
    }


    private void assertIdNotExist(Long id) {
        priceRepository.findById(id).ifPresent(m -> {
            throw new ConflictException("Price id already exists: " + id);
        });
    }

    @Override
    public Price getProductByBrandIdProductIdAndDate(Long brandId, Long productId, LocalDateTime date) {
        return priceRepository.getProductByBrandIdProductIdAndDate(brandId, productId, date)
                .orElseThrow(() -> new PriceNotFoundException("Price not found"))
                .toPrice();
    }
}