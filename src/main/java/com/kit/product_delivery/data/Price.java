package com.kit.product_delivery.data;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant startDate;
    private Instant endDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name="supply_id")
    private Supplier supplier;

    private Double price;
    private Double weight;
}
