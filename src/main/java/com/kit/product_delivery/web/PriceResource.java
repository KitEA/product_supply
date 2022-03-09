package com.kit.product_delivery.web;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class PriceResource {
    private Instant startDate;
    private Instant endDate;
    private BigDecimal price;
    private Integer weight;
    private String productName;
}
