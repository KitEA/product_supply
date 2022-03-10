package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Supply;
import com.kit.product_delivery.data.SupplyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
