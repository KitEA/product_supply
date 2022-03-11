package com.kit.productsupply.data.repository;

import com.kit.productsupply.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Product findByName(String name);
}
