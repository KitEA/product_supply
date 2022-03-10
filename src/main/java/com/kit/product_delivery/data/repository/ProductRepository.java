package com.kit.product_delivery.data.repository;

import com.kit.product_delivery.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Product findByName(String name);
}
