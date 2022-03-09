package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
