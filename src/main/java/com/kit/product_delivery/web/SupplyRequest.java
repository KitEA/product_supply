package com.kit.product_delivery.web;

import lombok.Getter;

import java.util.Set;

@Getter
public class SupplyRequest {
    String supplierName;
    Set<PriceResource> prices;
}
