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
    @JoinColumn(name="fruit_id")
    private Product fruit;

    @ManyToOne
    @JoinColumn(name="supply_id")
    private Supplier supplier;

    private Double price;
    private Double weight;
}
