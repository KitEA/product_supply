package com.kit.productsupply.data.repository;

import com.kit.productsupply.data.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
    Supplier findByName(String name);
}
