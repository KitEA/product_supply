package com.kit.productsupply.web.resources;

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
