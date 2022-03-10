package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where p.startDate >= ?1 and p.endDate <= ?2")
    List<Price> findAll(Date startDate, Date endDate);
}
