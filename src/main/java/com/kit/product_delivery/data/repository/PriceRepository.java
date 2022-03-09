package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
