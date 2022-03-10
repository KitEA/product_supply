package com.kit.product_delivery.web.resources;

import lombok.Value;

import java.util.Set;

@Value
public class SupplyRequest {
    String supplierName;
    Set<PriceResource> prices;
}
