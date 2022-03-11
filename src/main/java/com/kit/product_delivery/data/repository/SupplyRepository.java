package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

    @Query("select p from Supply p where p.startDate >= ?1 and p.endDate <= ?2")
    List<Supply> findAll(Date startDate, Date endDate);
}
