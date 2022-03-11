package com.kit.productsupply.web.resources;

import lombok.Value;

import java.util.Set;

@Value
public class SupplyRequest {
    String supplierName;
    Set<PriceResource> prices;
}
