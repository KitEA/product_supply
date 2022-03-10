package com.kit.product_delivery.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyView {
    private final String supplier;
    private final String product;
    private final BigDecimal cost;
    private final Integer weight;
}
