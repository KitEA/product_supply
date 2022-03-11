package com.kit.product_delivery.web.resources;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class ProductResource {
    BigDecimal price;
    int weight;
    String name;
}
